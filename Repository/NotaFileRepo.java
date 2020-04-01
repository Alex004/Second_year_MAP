package Repository;

import entities.Nota;
import UI.Pair;
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
import java.time.LocalDate;

public class NotaFileRepo extends InMemoryRepository<Pair<Long,Long>, Nota> {
    private String filepath;

    public NotaFileRepo(Validator<Nota> validator, String filepath) {
        super(validator);
        this.filepath = filepath;
        load();
    }

    private void load(){
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("C:\\Users\\punga\\Desktop\\gradle\\src\\main\\resources\\view\\Nota.xml");
            NodeList temaList=d.getElementsByTagName("notare");
            for(int i=0;i<temaList.getLength();i++)
            {
                Node p=temaList.item(i);
                if(p.getNodeType()==Node.ELEMENT_NODE)
                {
                    Long id_tema=null;
                    Long id_student=null;
                    Float nota=null;
                    Integer intarziere=null;
                    String data=null;
                    String profesor=null;
                    String feedback=null;
                    Element tema=(Element)p;

                    id_tema=Long.parseLong(tema.getAttribute("id_tema"));
                    id_student=Long.parseLong(tema.getAttribute("id_student"));

                    intarziere=Integer.parseInt(tema.getElementsByTagName("intarziere").item(0).getTextContent());
                    nota=Float.parseFloat(tema.getElementsByTagName("nota").item(0).getTextContent());
                    data=tema.getElementsByTagName("data").item(0).getTextContent();
                    profesor=tema.getElementsByTagName("profesor").item(0).getTextContent();
                    feedback=tema.getElementsByTagName("feedback").item(0).getTextContent();


                    Nota not=new Nota(nota, LocalDate.parse(data),intarziere,profesor,id_tema,id_student,feedback);
                    saveC(not);
                    System.out.println(findOne(not.getId()));
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

    public Nota saveC(Nota entity)
    {
        return super.save(entity);
    }

    public void addT(Nota entity)
    {
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        Long id_tema=entity.getId().getX();
        Long id_student=entity.getId().getX();
        Float nota=entity.getNota();
        Integer intarziere=entity.getIntarziere();
        String data=entity.getPredare().toString();
        String profesor=entity.getProf();
        String feedback=entity.getFeedback();
        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("Nota.xml");
            Node studentList=d.getElementsByTagName("note_list").item(0);
            Element person=d.createElement("notare");
            Attr attr = d.createAttribute("id_tema");
            attr.setValue(id_tema.toString());
            person.setAttributeNode(attr);

            Attr attr1 = d.createAttribute("id_student");
            attr1.setValue(id_student.toString());
            person.setAttributeNode(attr1);

            Element notaE=d.createElement("nota");
            notaE.appendChild(d.createTextNode(nota.toString()));
            person.appendChild(notaE);

            Element intarziereE=d.createElement("intarziere");
            intarziereE.appendChild(d.createTextNode(intarziere.toString()));
            person.appendChild(intarziereE);

            Element dataE=d.createElement("data");
            dataE.appendChild(d.createTextNode(data));
            person.appendChild(dataE);

            Element profesorE=d.createElement("profesor");
            profesorE.appendChild(d.createTextNode(profesor.toString()));
            person.appendChild(profesorE);

            Element feedbackE=d.createElement("feedback");
            feedbackE.appendChild(d.createTextNode(feedback.toString()));
            person.appendChild(feedbackE);

            studentList.appendChild(person);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File("Nota.xml"));
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

    @Override
    public Nota save(Nota entity) {
        addT(entity);
        return super.save(entity);
    }

    @Override
    public Nota delete(Pair<Long, Long> longLongPair) {
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("Nota.xml");
            Node nota=d.getElementsByTagName("note_list").item(0);

            NodeList notaList=nota.getChildNodes();

            for(int i=0;i<notaList.getLength();i++)
            {
                Node not = notaList.item(i);
                if(not.getNodeType()==Node.ELEMENT_NODE)
                {
//                    Attr attr =  pers.getAttributes();
//                    Node nodeAttr = attr.getNamedItem("id");
                    Element at=(Element) not;
//                    System.out.println(at.getAttributes("id"));

                    if(longLongPair.getX()==Long.parseLong(at.getAttribute("id_tema"))&&longLongPair.getY()==Long.parseLong(at.getAttribute("id_student")))
                    {
                        nota.removeChild(not);
                        i--;
                    }


                }
            }




            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File("Nota.xml"));
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

        return super.delete(longLongPair);
    }

    @Override
    public Nota findOne(Pair<Long, Long> longLongPair) {
        return super.findOne(longLongPair);
    }

    @Override
    public Iterable<Nota> findAll() {
        return super.findAll();
    }

    @Override
    public Nota update(Nota entity) {

        Nota n=super.update(entity);
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        Long id_tema=entity.getId().getX();
        Long id_student=entity.getId().getX();
        Float nota=entity.getNota();
        Integer intarziere=entity.getIntarziere();
        String data=entity.getPredare().toString();
        String profesor=entity.getProf();
        String feedback=entity.getFeedback();

        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("Nota.xml");
            NodeList nodeList=d.getElementsByTagName("notare");

            for(int i=0;i<nodeList.getLength();i++)
            {
                Node pers = nodeList.item(i);
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
                                if ("nota".equals(no.getNodeName())) {
                                    no.setTextContent(nota.toString());
                                }

                                if ("intarziere".equals(no.getNodeName())) {
                                    no.setTextContent(intarziere.toString());
                                }
                                if ("data".equals(no.getNodeName())) {
                                    no.setTextContent(data);
                                }
                                if ("profesor".equals(no.getNodeName())) {
                                    no.setTextContent(profesor);
                                }
                                if ("feedback".equals(no.getNodeName())) {
                                    no.setTextContent(feedback);
                                }
                            }
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File("Nota.xml"));
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
        return super.update(entity);
    }
}
