package com.LukeHackett;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class HarnessRecords {
    private ArrayList<Harness> harnesses;

    public HarnessRecords() {
        harnesses = new ArrayList<>();
    }

    public HarnessRecords(BufferedReader records) throws IOException {
        harnesses = new ArrayList<>();
        int numHarnesses = records.read();
        for (int i = 0; i < numHarnesses; i++) {
            String[] harness = records.readLine().split(" ");
            if (harness.length == 6) {
                harnesses.add(new Harness(harness[0], Integer.valueOf(harness[1]), Integer.valueOf(harness[2]), harness[3], Boolean.valueOf(harness[4]), harness[5]));
            } else {
                harnesses.add(new Harness(harness[0], Integer.valueOf(harness[1]), harness[2]));
            }
        }
    }

    public boolean isEmpty() {
        return (harnesses.size() == 0);
    }

    public void addHarness(Harness harness) {
        harnesses.add(harness);
    }

    public Harness findHarness(String make, int modelNo) {
        for (Harness h : harnesses) {
            if (h.getMake().equals(make) && h.getModelNo() == modelNo) return h;
        }
        return null;
    }

    public boolean checkHarness(String instructor, String make, int modelNo) {
        int index = 0;
        Harness harness = null;
        while (harness == null && index < harnesses.size()) {
            if (harnesses.get(index).getMake().equals(make) && harnesses.get(index).getModelNo() == modelNo)
                harness = harnesses.get(index);
            index++;
        }
        if (harness != null && harness.canHarnessBeLoaned()) {
            harness.checkHarness(instructor);
            return true;
        }
        return false;
    }

    public boolean loanHarness(String user) {
        int index = 0;
        Harness harness = null;
        while (harness == null && index < harnesses.size()) {
            if (harnesses.get(index).canHarnessBeLoaned()) harness = harnesses.get(index);
            index++;
        }
        if (harness != null) {
            harness.loanHarness(user);
            return true;
        }
        return false;
    }

    public boolean returnHarness(String make, int modelNo) {
        int index = 0;
        Harness harness = null;
        while (harness == null && index < harnesses.size()) {
            if (harnesses.get(index).getMake().equals(make) && harnesses.get(index).getModelNo() == modelNo)
                harness = harnesses.get(index);
            index++;
        }
        if (harness != null && harness.isHarnessOnLoan()) {
            harness.returnHarness();
            return true;
        }
        return false;
    }

    public boolean removeHarness(String make, int modelNo) {
        int index = 0;
        boolean removed = false;
        while (!removed && index < harnesses.size()) {
            if (harnesses.get(index).getMake().equals(make) && harnesses.get(index).getModelNo() == modelNo) {
                harnesses.remove(index);
                removed = true;
            }
            index++;
        }
        return removed;
    }
}