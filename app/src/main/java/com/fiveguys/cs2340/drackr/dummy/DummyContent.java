package com.fiveguys.cs2340.drackr.dummy;

import com.fiveguys.cs2340.drackr.Charity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static ArrayList<Charity> charities = new ArrayList<Charity>();

    public static void setup(ArrayList<Charity> charities) {
        for (Charity charity: charities) {
            DummyItem d = new DummyItem(charity);
            addItem(d);
        }
    }

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    //private static final int COUNT = 6;
    private static int getCount() {
        return charities.size();
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(charities.get(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final Charity charity;

        public DummyItem(Charity charity) {
            this.id = charity.getKey();
            this.charity = charity;
        }

        @Override
        public String toString() {
            return charity.getName();
        }
    }
}
