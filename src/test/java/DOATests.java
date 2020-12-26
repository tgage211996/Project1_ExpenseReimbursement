import com.ex.webapp.DOA.Employee;
import com.ex.webapp.DOA.Manager;
import com.ex.webapp.DOA.Reimbursement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DOATests {

    @Test
    public void checkEmployeeTest() throws IOException {
        boolean rst = Employee.getInstance().checkEmployee("thomasbengage@gmail.com","1Ab23456");
        Assert.assertNotNull(rst);
    }

    @Test
    public void checkEmployeeTest2() throws IOException {
        boolean rst = Employee.getInstance().checkEmployee("thomasbengage@gmail.com","1Ab23456");
        Assert.assertNotNull(rst);
    }


    @Test
    public void GetEmployeeDataTest() throws IOException {
        JSONObject obj = Employee.getInstance().getEmployeeData("thomasbengage@gmail.com","1Ab23456");
        Assert.assertNotNull(obj);
    }

    @Test
    public void checkManagerTest() throws IOException {
        boolean rst = Manager.getInstance().checkManager("leopurell@gmail.com", "123456");
        Assert.assertTrue(rst);
    }

    @Test
    public void getManagerDataTest() throws IOException {
        JSONObject obj = Manager.getInstance().getManagerData("thomasben@gmail.com", "1Ab23456");
        Assert.assertNotNull(obj);
    }

    @Test
    public void getAllEmpTest() throws IOException{
        JSONArray array = Employee.getInstance().getAllEmp();
        Assert.assertNotNull(array);
    }

    @Test
    public void updateEmpTest() throws IOException{
        Employee emp = Employee.getInstance();
        emp.updateEmp("Thomas","Gage","thomasbengage@gmail.com","Ab23456","221B Baker St");

    }

    @Test
    public void submitReimbursementTest(){
        Reimbursement rmb = Reimbursement.getInstance();
        rmb.submitReimbursement(40.00,"sampengear@gmail.com", "12/2/2020");
    }

    @Test
    public void getPenReimbursementTest() throws IOException {
        Reimbursement rmb = Reimbursement.getInstance();
        JSONArray array = rmb.getPenReimbursement("thomasbengage@gmail.com");
        Assert.assertNotNull(array);
    }

    @Test
    public void getResReimbursementTest() throws IOException {
        Reimbursement rmb = Reimbursement.getInstance();
        JSONArray array = rmb.getResReimbursement("thomasbengage@gmail.com");
        Assert.assertNotNull(array);
    }

    @Test
    public void getAllPenRemTest() throws IOException {
        Reimbursement rmb = Reimbursement.getInstance();
        JSONArray array = rmb.getAllPenRem();
        Assert.assertNotNull(array);
    }

    @Test
    public void getAllResRemTest() throws IOException {
        Reimbursement rmb = Reimbursement.getInstance();
        JSONArray array = rmb.getAllResRem();
        Assert.assertNotNull(array);
    }

    @Test
    public void getEmpReims() throws IOException {
        Reimbursement rmb = Reimbursement.getInstance();
        JSONArray array = rmb.getEmpReims("thomasbengage@gmail.com");
        Assert.assertNotNull(array);
    }

    @Test
    public void approveDenyReimbursementTest() throws IOException {
        Reimbursement rmb = Reimbursement.getInstance();
        rmb.approveDenyReimbursement("pending",5,1);
    }


}
