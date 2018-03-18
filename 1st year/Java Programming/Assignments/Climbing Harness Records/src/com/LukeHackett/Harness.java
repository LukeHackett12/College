package com.LukeHackett;

public class Harness {
    private String make;
    private int modelNo;
    private int numUses;
    private String lastInstructor;
    private boolean loaned;
    private String loanUser;

    Harness(String make, int modelNo, int numUses, String lastInstructor, boolean loaned, String loanUser) {
        this.make = make;
        this.modelNo = modelNo;
        this.numUses = numUses;
        this.lastInstructor = lastInstructor;
        this.loaned = loaned;
        if (loaned) this.loanUser = loanUser;
        else this.loanUser = null;
    }

    Harness(String make, int modelNo, String lastInstructor) {
        this.make = make;
        this.modelNo = modelNo;
        this.numUses = 0;
        this.lastInstructor = lastInstructor;
        this.loaned = false;
        this.loanUser = null;
    }

    public void checkHarness(String instructor) {
        numUses = 0;
        lastInstructor = instructor;
    }

    public boolean isHarnessOnLoan() {
        return loaned;
    }

    public boolean canHarnessBeLoaned() {
        return !loaned;
    }

    public void loanHarness(String user) {
        loanUser = user;
        loaned = true;
    }

    public void returnHarness() {
        if (loaned) {
            loaned = false;
            loanUser = null;
        }
    }

    public String getMake() {
        return make;
    }

    public int getModelNo() {
        return modelNo;
    }

    public int getNumUses() {
        return numUses;
    }

    public String getLastInstructor() {
        return lastInstructor;
    }

    @Override
    public String toString() {
        return "Harness{" +
                "make='" + make + '\'' +
                ", modelNo=" + modelNo +
                ", numUses=" + numUses +
                ", lastInstructor='" + lastInstructor + '\'' +
                ", loaned=" + loaned +
                ", loanUser='" + loanUser + '\'' +
                '}';
    }
}
