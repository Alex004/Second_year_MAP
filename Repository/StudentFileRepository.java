package Repository;

import entities.Student;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudentFileRepository extends InMemoryRepository<Long,Student>{
    private String filename;

    public StudentFileRepository(Validator validator, String filename) {
        super(validator);
        this.filename = filename;
        loadData();
    }

    private void loadData() {
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse(String.valueOf(getClass().getResource("/view/Student.xml")));
            NodeList studentList=d.getElementsByTagName("person");
            for(int i=0;i<studentList.getLength();i++)
            {
                Node p=studentList.item(i);
                if(p.getNodeType()==Node.ELEMENT_NODE)
                {
                    Long id=null;
                    String nume=null;
                    Integer grupa=null;
                    Element person=(Element)p;

                    id=Long.parseLong(person.getAttribute("id"));
                    nume=person.getElementsByTagName("nume").item(0).getTextContent();
                    grupa=Integer.parseInt(person.getElementsByTagName("grupa").item(0).getTextContent());
                    Student stud=new Student(nume,grupa,id);
                    saveC(stud);
                    System.out.println(findOne(stud.getId()));
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

    public Student saveC(Student entity) {
        return super.save(entity);
    }

    @Override
    public Student save(Student entity) {
        addS(entity);
        return super.save(entity);
    }

    public void addS(Student entity)
    {
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        Long id=entity.getId();
        String nume=entity.getName();
        Integer grupa=entity.getGrupa();
        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse("resources\\view\\Student.xml");
            Node studentList=d.getElementsByTagName("student").item(0);
            Element person=d.createElement("person");
            Attr attr = d.createAttribute("id");
            attr.setValue(id.toString());
            person.setAttributeNode(attr);
            Element numeE=d.createElement("nume");
            numeE.appendChild(d.createTextNode(nume));
            person.appendChild(numeE);
            Element grupaE=d.createElement("grupa");
            grupaE.appendChild(d.createTextNode(grupa.toString()));
            person.appendChild(grupaE);
            studentList.appendChild(person);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File(String.valueOf(getClass().getResource("/view/Student.xml"))));
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
    public Student delete(Long aLong) {

        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse(String.valueOf(getClass().getResource("/view/Student.xml")));
            Node student=d.getElementsByTagName("student").item(0);

            NodeList studentList=student.getChildNodes();

            for(int i=0;i<studentList.getLength();i++)
            {
                Node pers = studentList.item(i);
                if(pers.getNodeType()==Node.ELEMENT_NODE)
                {


//                    Attr attr =  pers.getAttributes();
//                    Node nodeAttr = attr.getNamedItem("id");
                    Element at=(Element) pers;
//                    System.out.println(at.getAttributes("id"));

                    if(aLong==Long.parseLong(at.getAttribute("id")))
                    {
                        student.removeChild(pers);
                        i--;
                    }


                }
            }




            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(new File(String.valueOf(getClass().getResource("/view/Student.xml"))));
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
    public Student findOne(Long aLong) {
        return super.findOne(aLong);
    }

    @Override
    public Iterable<Student> findAll() {
        return super.findAll();
    }

    @Override
    public Student update(Student entity) {
        System.out.println("merge");
        Student s=super.update(entity);
        DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
        Long id=entity.getId();
        String nume=entity.getName();
        Integer grupa=entity.getGrupa();

        try {
            DocumentBuilder document=doc.newDocumentBuilder();
            Document d=document.parse(String.valueOf(getClass().getResource("/view/Student.xml")));
            NodeList studentList=d.getElementsByTagName("person");


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
                                    if ("nume".equals(no.getNodeName())) {
                                        no.setTextContent(nume);
                                    }

                                    if ("grupa".equals(no.getNodeName())) {
                                        no.setTextContent(grupa.toString());
                                    }
                                }
                            }
                        }
                    }
                }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            System.out.println("scrie");
            StreamResult result = new StreamResult(new File("/resources/view/Student.xml"));
            transformer.transform(source, result);
            System.out.println("A scris");

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
        return s;
    }



    //    void parseLine(String line){
//        String[] fields=line.split(";");
//        Student st=new Student(fields[1],Integer,Long.parseLong(fields[2]));
//        st.setId(Long.parseLong(fields[0]));
//        super.save(st);
//    }
}
