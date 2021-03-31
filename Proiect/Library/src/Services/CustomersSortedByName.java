package Services;
import Models.Customer;
import java.util.Comparator;

public class CustomersSortedByName implements Comparator<Customer> {

        @Override
        public int compare(Customer s1, Customer s2) {
            return s1.getName().compareTo(s2.getName());
        }
}
