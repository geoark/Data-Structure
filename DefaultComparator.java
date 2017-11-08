import java.util.Comparator;

public class DefaultComparator implements Comparator<PrintJob> {
	@Override
	public int compare(PrintJob a, PrintJob b) {
		return (a).compareTo(b);
	}
}
