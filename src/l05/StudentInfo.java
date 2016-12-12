
package l05;

import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author wang
 * 第二题
 */
public class StudentInfo {
    public HashMap<Integer, Student> studentsHashMap=new HashMap<Integer, Student>();
    Student tempStudent;
    /**
     * @param num
     * @param name
     * @param grade
     */
    public void save(Integer num,String name,Double grade){
        tempStudent = new Student(name, grade);
        studentsHashMap.put(num, tempStudent);
    }
    /**
     * 从内存中读取，格式化输出
     */
    public void load(){
        Set<Integer> set = studentsHashMap.keySet();
        Iterator<Integer> i = set.iterator();
        Iterator<Integer> i2 = set.iterator();
        while(i.hasNext()){
        	Student temp = studentsHashMap.get(i.next());
        	System.out.println(i2.next().toString()+"\t"+temp.getName()+"\t"+temp.getGrade());
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.save(71118501, "张三", 80.0);
        studentInfo.save(71118502, "李四", 79.5);
        studentInfo.save(71118503, "王五", 91.0);
        studentInfo.save(71118504, "赵六", 60.0);
        studentInfo.save(71118505, "宋七", 18.6);
        
        studentInfo.load();
    }
}
