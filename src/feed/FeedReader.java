package feed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class FeedReader {
        static final String TITLE = "title";
        static final String CATEGORY = "category";
        static final String LINK = "link";
        static final String AUTHOR = "author";
        static final String ITEM = "item";
        static final String PUB_DATE = "pubDate";
        static final String GUID = "guid";

        final URL url;

        public FeedReader(String feedUrl) {
                try {
                        this.url = new URL(feedUrl);
                } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                }
        }

        public Feed readFeed() {
                Feed feed = null;
                try {
                        boolean isFeedHeader = true;
                        // Set header values intial to the empty string
                        String category = "";
                        String title = "";
                        String link = "";
                        String language = "";
                        String copyright = "";
                        String author = "";
                        String pubdate = "";
                        String guid = "";

                        // First create a new XMLInputFactory
                        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
                        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
                        inputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
                        // Setup a new eventReader
                        InputStream in = read();
                        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                        // read the XML document
                        while (eventReader.hasNext()) {
                                XMLEvent event = eventReader.nextEvent();
                                if (event.isStartElement()) {
                                        String localPartPrevius = event.asStartElement().getName()
                                                        .getLocalPart();
                                        String localPart = localPartPrevius;
                                        switch (localPart) {
                                        case ITEM:
                                                if (isFeedHeader) {
                                                        isFeedHeader = false;
                                                        feed = new Feed(title, link, category, language, copyright, pubdate, guid);
                                                }
                                                event = eventReader.nextEvent();
                                                break;
                                        case TITLE:
                                                title = getCharacterData(event, eventReader);
                                                break;
                                        case CATEGORY:
                                        		category = getCharacterData(event, eventReader);
                                                //System.out.println(category);
                                                break;
                                        case LINK:
                                                link = getCharacterData(event, eventReader);
                                                break;
                                        case GUID:
                                                guid = getCharacterData(event, eventReader);
                                                break;
                                        case AUTHOR:
                                                author = getCharacterData(event, eventReader);
                                                break;
                                        case PUB_DATE:
                                                pubdate = getCharacterData(event, eventReader);
                                                break;
                                        }
                                } else if (event.isEndElement()) {
                                        if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                                                FeedMessage message = new FeedMessage();
                                                message.setAuthor(author);
                                                message.setCategory(category);
                                                message.setGuid(guid);
                                                message.setLink(link);
                                                message.setTitle(title);
                                                message.setDate(pubdate);
                                                feed.getMessages().add(message);
                                                event = eventReader.nextEvent();
                                                continue;
                                        }
                                }
                        }
                } catch (XMLStreamException e) {
                        throw new RuntimeException(e);
                }
                return feed;
        }

        private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
                        throws XMLStreamException {
                String result = "";
                event = eventReader.nextEvent();
                if (event instanceof Characters) {
                        result = event.asCharacters().getData();
                }
                return result;
        }

        private InputStream read() {
                try {
                        return url.openStream();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

}