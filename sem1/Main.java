package sem1;//package ubb.scs.map.ir.sem1;
//
//
//import entities.Student;
//import ubb.scs.map.ir.sem1.containers.Strategy;
//import ubb.scs.map.ir.sem1.model.MessageTask;
//import ubb.scs.map.ir.sem1.model.Task;
//import ubb.scs.map.ir.sem1.runner.PrinterTaskRunner;
//import ubb.scs.map.ir.sem1.runner.StrategyTaskRunner;
//import ubb.scs.map.ir.sem1.runner.TaskRunner;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//public class Main {
//
//    public static MessageTask[] createMessageTaskArray() {
//        MessageTask t1 = new MessageTask("1", "Feedback lab1",
//                "Ai obtinut 9.60", "Gigi", "Ana", LocalDateTime.now());
//        MessageTask t2 = new MessageTask("2", "Feedback lab1",
//                "Ai obtinut 5.60", "Gigi", "Ana", LocalDateTime.now());
//        MessageTask t3 = new MessageTask("3", "Feedback lab3",
//                "Ai obtinut 10", "Gigi", "Ana", LocalDateTime.now());
//        return new MessageTask[]{t1, t2, t3};
//    }
//    public static void testSmecher(){
//        MessageTask[] l = createMessageTaskArray();
//        TaskRunner runner = new StrategyTaskRunner(Strategy.valueOf("LIFO"));
//        for (Task t : l
//        ) {
//            runner.addTask(t);
//        }
//        runner.executeAll();
//        for (Task t : l
//        ) {
//            runner.addTask(t);
//        }
//        TaskRunner decorator = new PrinterTaskRunner(runner);
//        decorator.executeOneTask();
//    }
//    public static void main(String[] args) {
//        Student s1 = new Student("Dan",4.5f);
//        Student s2 = new Student("Ana",4.2f);
//        Student s3 = new Student("Dan",4.5f);
//        Student s4 = new Student("Andrei",1.1f);
//        Student s5 = new Student("Barbu",10.0f);
//        Set<Student> set = new HashSet<>();
////        set.add(s1);
////        set.add(s2);
////        set.add(s3);
////        set.add(s4);
////        set.add(s5);
//        set.addAll(Arrays.asList(s1,s2,s3,s4,s5));
//        System.out.println(set);
//
//        Set<Student> treeset1 = new TreeSet<>(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return (int)(o2.getMedia() - o1.getMedia());
//            }
//        });
//        treeset1.addAll(Arrays.asList(s1,s2,s3,s4,s5));
//        System.out.println(treeset1);
//
//        Map<String,Student> hashmap = new HashMap<>();
//        hashmap.put(s1.getName(),s1);
//        hashmap.put(s2.getName(),s2);
//        hashmap.put(s3.getName(),s3);
//        hashmap.put(s4.getName(),s4);
//        hashmap.put(s5.getName(),s5);
//        for (String key:hashmap.keySet()
//             ) {
//            //System.out.println("Key= "+ key + " value= " + hashmap.get(key));
//        }
//        for ( Map.Entry<String, Student> pair:hashmap.entrySet()
//        ) {
//            //System.out.println("Key= "+ pair.getKey() + " value= " + pair.getValue());
//        }
//
//    }
//
//}
//
//
