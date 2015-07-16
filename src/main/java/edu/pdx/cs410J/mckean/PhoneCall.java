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
     * @param caller String that represents the person making the call
     * @param callee String that represents the person receiving the call
     * @param endTime String that represents the time the call ended
     * @param startTime String that represents the time the call started
     */
    public PhoneCall(String caller, String callee, String startTime, String endTime) {
        this.Caller = caller;
        this.Callee = callee;
        this.StartTime = startTime;
        this.EndTime = endTime;
    }

    /**
     * @return Returns the phone number of the person who originated this phone call
     */
    @Override
    public String getCaller() {
        return Caller;
    }

    /**
     * @return Returns the phone number of the person who received this phone call.
     */
    @Override
    public String getCallee() {
        return Callee;
    }

    /**
     * @return Returns a textual representation of the time that this phone call was originated.
     */
    @Override
    public String getStartTimeString() {
        return StartTime;
    }

    /**
     * @return Returns a textual representation of the time that this phone call was completed.
     */
    @Override
    public String getEndTimeString() {
        return EndTime;
    }
}
