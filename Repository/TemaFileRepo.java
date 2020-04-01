package Repository;

import entities.Student;
import entities.Tema;
import entities.validators.Validator;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class TemaFileRepo extends InMemoryRepository<Long,Tema> {
    private String filename;

    public TemaFileRepo(Validator validator, String filename) {
        super(validator);
        this.filename = filename;
        load();
    }

    private void load()
    {
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse(String.valueOf(getClass().getResource("/view/Tema.xml")));
            NodeList temaList=d.getElementsByTagName("tema");
            for(int i=0;i<temaList.getLength();i++)
            {
                Node p=temaList.item(i);
                if(p.getNodeType()==Node.ELEMENT_NODE)
                {
                    Long id=null;
                    Integer sem=null;
                    Integer durata=null;
                    String data=null;
                    String descriere=null;
                    Element tema=(Element)p;

                    id=Long.parseLong(tema.getAttribute("id"));

                    sem=Integer.parseInt(tema.getElementsByTagName("sem").item(0).getTextContent());
                    durata=Integer.parseInt(tema.getElementsByTagName("durata").item(0).getTextContent());
                    data=tema.getElementsByTagName("start_date").item(0).getTextContent();
                    descriere=tema.getElementsByTagName("descriere").item(0).getTextContent();

                    Tema tem=new Tema(id,sem,durata,data,descriere);
                    saveC(tem);
                    System.out.println(findOne(tem.getId()));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addT(Tema entity)
    {
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        Long id=entity.getId();
        Integer sem=entity.getSem();
        Integer durata=entity.getDurata();
        String data=entity.getSdate().toString();
        String descriere=entity.getDescriere();
        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("Tema.xml");
            Node studentList=d.getElementsByTagName("curs").item(0);
            Element person=d.createElement("tema");
            Attr attr = d.createAttribute("id");
            attr.setValue(id.toString());
            person.setAttributeNode(attr);
            Element numeE=d.createElement("sem");
            numeE.appendChild(d.createTextNode(sem.toString()));
            person.appendChild(numeE);
            Element durataE=d.createElement("durata");
            durataE.appendChild(d.createTextNode(durata.toString()));
            person.appendChild(durataE);
            Element dataE=d.createElement("start_date");
            dataE.appendChild(d.createTextNode(data));
            person.appendChild(dataE);
            Element descriereE=d.createElement("descriere");
            descriereE.appendChild(d.createTextNode(durata.toString()));
            person.appendChild(descriereE);
            studentList.appendChild(person);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File("Tema.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public Tema saveC(Tema tema)
    {
        return super.save(tema);
    }

    @Override
    public Tema save(Tema entity) {
        addT(entity);
        return super.save(entity);
    }

    @Override
    public Tema delete(Long aLong) {

        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("Tema.xml");
            Node tema=d.getElementsByTagName("curs").item(0);

            NodeList temaList=tema.getChildNodes();

            for(int i=0;i<temaList.getLength();i++)
            {
                Node pers = temaList.item(i);
                if(pers.getNodeType()==Node.ELEMENT_NODE)
                {
//                    Attr attr =  pers.getAttributes();
//                    Node nodeAttr = attr.getNamedItem("id");
                    Element at=(Element) pers;
//                    System.out.println(at.getAttributes("id"));

                    if(aLong==Long.parseLong(at.getAttribute("id")))
                    {
                        tema.removeChild(pers);
                        i--;
                    }


                }
            }




            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File("Tema.xml"));
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return super.delete(aLong);
    }

    @Override
    public Tema findOne(Long aLong) {
        return super.findOne(aLong);
    }

    @Override
    public Iterable<Tema> findAll() {
        return super.findAll();
    }

    @Override
    public Tema update(Tema entity) {
        Tema t=super.update(entity);
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        Long id=entity.getId();
        Integer sem=entity.getSem();
        Integer durata=entity.getDurata();
        String data=entity.getSdate().toString();
        String descriere=entity.getDescriere();

        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("Tema.xml");
            NodeList studentList=d.getElementsByTagName("tema");

            for(int i=0;i<studentList.getLength();i++)
            {
                Node pers = studentList.item(i);
                if(pers.getNodeType()==Node.ELEMENT_NODE)
                {
//                    Attr attr =  pers.getAttributes();
//                    Node nodeAttr = attr.getNamedItem("id");
//                    Element at=(Element) pers;
//                    System.out.println(at.getAttributes("id"));

                    Element p=(Element)pers;
                    if (entity.getId().toString().equals(p.getAttribute("id"))) {
                        NodeList nod=pers.getChildNodes();
                        for(int j=0;j<nod.getLength();j++)
                        {
                            Node no=nod.item(j);
                            if(no.getNodeType()==Node.ELEMENT_NODE)
                            {
                                if ("sem".equals(no.getNodeName())) {
                                    no.setTextContent(sem.toString());
                                }

                                if ("durata".equals(no.getNodeName())) {
                                    no.setTextContent(durata.toString());
                                }
                                if ("start_date".equals(no.getNodeName())) {
                                    no.setTextContent(data);
                                }
                                if ("descriere".equals(no.getNodeName())) {
                                    no.setTextContent(descriere);
                                }
                            }
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File("Tema.xml"));
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return t;
    }
}
