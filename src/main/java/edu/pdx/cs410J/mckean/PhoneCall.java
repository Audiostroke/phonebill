package edu.pdx.cs410J.mckean;

import edu.pdx.cs410J.AbstractPhoneCall;

/**
 * Created by TylerMcKean on 7/8/15.
 */
public class PhoneCall extends AbstractPhoneCall {


    /**
     * Data members for a phone call object including the phone number of the caller
     * the phone number of who is being called, the data and time the call started,
     * and the data and time the call was terminated.
     */
    private String Caller;
    private String Callee;
    private String StartTime;
    private String EndTime;

    /**
     * Constructor that sets the data members of an individual phone call
     * based on what is read in from the command line.
     * @param callee
     * @param caller
     * @param endTime
     * @param startTime
     */
    public PhoneCall(String caller, String callee, String startTime, String endTime) {
        this.Caller = caller;
        this.Callee = callee;
        this.StartTime = startTime;
        this.EndTime = endTime;
    }

    /**
     * Returns the phone number of the person who originated this phone
     * call.
     */
    @Override
    public String getCaller() {
        return Caller;
    }

    /**
     * Returns the phone number of the person who received this phone
     * call.
     */
    @Override
    public String getCallee() {
        return Callee;
    }

    /**
     * Returns a textual representation of the time that this phone call
     * was originated.
     */
    @Override
    public String getStartTimeString() {
        return StartTime;
    }

    /**
     * Returns a textual representation of the time that this phone call
     * was completed.
     */
    @Override
    public String getEndTimeString() {
        return EndTime;
    }
}
