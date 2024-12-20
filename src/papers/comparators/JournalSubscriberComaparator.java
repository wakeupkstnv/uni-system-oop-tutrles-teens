package papers.comparators;

import papers.Journal;

import java.util.Comparator;

public class JournalSubscriberComaparator implements Comparator<Journal> {
    @Override
    public int compare(Journal o1, Journal o2) {
        return Integer.compare(o1.getSubscribers().size(), o2.getSubscribers().size());
    }
}
