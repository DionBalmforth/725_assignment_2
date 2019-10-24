/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK ConveyorCTLMulti
  * @author JHC
  * @version 20191024/JHC
  */
public class ConveyorCTLMulti extends FBInstance
{
/** Input event qualifier */
  public BOOL PE = new BOOL();
/** VAR Block */
  public BOOL Block = new BOOL();
/** VAR Candidate */
  public BOOL Candidate = new BOOL();
/** VAR PE14 */
  public BOOL PE14 = new BOOL();
/** Output event qualifier */
  public BOOL MotoRotate = new BOOL();
/** VAR BlockCon */
  public BOOL BlockCon = new BOOL();
/** VAR lastPE */
  public BOOL lastPE = new BOOL();
/** VAR lastBlock */
  public BOOL lastBlock = new BOOL();
/** VAR StoreEmit */
  public BOOL StoreEmit = new BOOL();
/** Initialization Request */
 public EventServer INIT = new EventInput(this);
/** Normal Execution Request */
 public EventServer REQ = new EventInput(this);
/** EVENT CAS_STOP */
 public EventServer CAS_STOP = new EventInput(this);
/** EVENT CAS_START */
 public EventServer CAS_START = new EventInput(this);
/** EVENT REPLY_IN */
 public EventServer REPLY_IN = new EventInput(this);
/** EVENT REQUEST_IN */
 public EventServer REQUEST_IN = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** EVENT STOP */
 public EventOutput STOP = new EventOutput();
/** EVENT START */
 public EventOutput START = new EventOutput();
/** EVENT REPLY_OUT */
 public EventOutput REPLY_OUT = new EventOutput();
/** EVENT REQUEST_OUT */
 public EventOutput REQUEST_OUT = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("REQ".equals(s)) return REQ;
    if("CAS_STOP".equals(s)) return CAS_STOP;
    if("CAS_START".equals(s)) return CAS_START;
    if("REPLY_IN".equals(s)) return REPLY_IN;
    if("REQUEST_IN".equals(s)) return REQUEST_IN;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("CNF".equals(s)) return CNF;
    if("STOP".equals(s)) return STOP;
    if("START".equals(s)) return START;
    if("REPLY_OUT".equals(s)) return REPLY_OUT;
    if("REQUEST_OUT".equals(s)) return REQUEST_OUT;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("PE".equals(s)) return PE;
    if("Block".equals(s)) return Block;
    if("Candidate".equals(s)) return Candidate;
    if("PE14".equals(s)) return PE14;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("MotoRotate".equals(s)) return MotoRotate;
    if("BlockCon".equals(s)) return BlockCon;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("PE".equals(ivName)) connect_PE((BOOL)newIV);
    else if("Block".equals(ivName)) connect_Block((BOOL)newIV);
    else if("Candidate".equals(ivName)) connect_Candidate((BOOL)newIV);
    else if("PE14".equals(ivName)) connect_PE14((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable PE
  * @param newIV The variable to connect
 */
  public void connect_PE(BOOL newIV){
    PE = newIV;
    }
/** Connect the given variable to the input variable Block
  * @param newIV The variable to connect
 */
  public void connect_Block(BOOL newIV){
    Block = newIV;
    }
/** Connect the given variable to the input variable Candidate
  * @param newIV The variable to connect
 */
  public void connect_Candidate(BOOL newIV){
    Candidate = newIV;
    }
/** Connect the given variable to the input variable PE14
  * @param newIV The variable to connect
 */
  public void connect_PE14(BOOL newIV){
    PE14 = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
  alg_START1();
  CNF.serviceEvent(this);
    if(StoreEmit.value) state_SEND();
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
  CNF.serviceEvent(this);
state_START();
}
private static final int index_SEND = 2;
private void state_SEND(){
  eccState = index_SEND;
  alg_SEND_P();
  REPLY_OUT.serviceEvent(this);
  CNF.serviceEvent(this);
state_START();
}
private static final int index_WANTED = 3;
private void state_WANTED(){
  eccState = index_WANTED;
  alg_WANTED_P();
  alg_STOP();
  STOP.serviceEvent(this);
  REQUEST_OUT.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_NEW_BAG = 4;
private void state_NEW_BAG(){
  eccState = index_NEW_BAG;
  alg_STOP();
  STOP.serviceEvent(this);
  alg_NEWBAG_P();
}
private static final int index_NEW_REQUEST = 5;
private void state_NEW_REQUEST(){
  eccState = index_NEW_REQUEST;
  alg_NEWREQUEST_P();
}
private static final int index_SEND2 = 6;
private void state_SEND2(){
  eccState = index_SEND2;
  alg_SEND_P();
  REPLY_OUT.serviceEvent(this);
state_WANTED();
}
private static final int index_STOP2 = 7;
private void state_STOP2(){
  eccState = index_STOP2;
  alg_STOP_P();
}
private static final int index_HOLD = 8;
private void state_HOLD(){
  eccState = index_HOLD;
  alg_START();
  START.serviceEvent(this);
  alg_HOLD_P();
}
private static final int index_STOP1 = 9;
private void state_STOP1(){
  eccState = index_STOP1;
  alg_STOP_P();
  alg_STOP();
  STOP.serviceEvent(this);
}
private static final int index_SEND1 = 10;
private void state_SEND1(){
  eccState = index_SEND1;
  alg_SEND1_P();
  REPLY_OUT.serviceEvent(this);
state_WANTED();
}
private static final int index_HELD = 11;
private void state_HELD(){
  eccState = index_HELD;
}
private static final int index_HOLD_REQUEST = 12;
private void state_HOLD_REQUEST(){
  eccState = index_HOLD_REQUEST;
  alg_Plz();
state_WANTED();
}
private static final int index_sdfgh = 13;
private void state_sdfgh(){
  eccState = index_sdfgh;
}
/** The default constructor. */
public ConveyorCTLMulti(){
    super();
    lastPE.initializeNoException("1");
    lastBlock.initializeNoException("0");
    StoreEmit.initializeNoException("0");
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
    else if (e == REQ) service_REQ();
    else if (e == CAS_STOP) service_CAS_STOP();
    else if (e == CAS_START) service_CAS_START();
    else if (e == REPLY_IN) service_REPLY_IN();
    else if (e == REQUEST_IN) service_REQUEST_IN();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_START)) state_INIT();
  }
/** Services the REQ event. */
  public void service_REQ(){
    if ((eccState == index_START) && (!PE.value)) state_WANTED();
    else if ((eccState == index_NEW_REQUEST) && (!PE14.value)) state_SEND();
    else if ((eccState == index_STOP2) && (!PE14.value)) state_SEND2();
    else if ((eccState == index_STOP1) && (!PE14.value)) state_SEND1();
    else if ((eccState == index_NEW_REQUEST) && (!PE.value)) state_STOP1();
    else if ((eccState == index_HOLD) && (PE.value)) state_HELD();
    else if ((eccState == index_HELD) && (!PE.value)) state_NEW_BAG();
    else if ((eccState == index_HELD) && (!PE14.value)) state_START();
    else if ((eccState == index_NEW_BAG) && (!PE14.value)) state_WANTED();
    else if ((eccState == index_START) && (PE.value)) state_SEND();
  }
/** Services the CAS_STOP event. */
  public void service_CAS_STOP(){
  }
/** Services the CAS_START event. */
  public void service_CAS_START(){
  }
/** Services the REPLY_IN event. */
  public void service_REPLY_IN(){
    if ((eccState == index_WANTED)) state_HOLD();
  }
/** Services the REQUEST_IN event. */
  public void service_REQUEST_IN(){
    if ((eccState == index_START)) state_SEND();
    else if ((eccState == index_NEW_BAG)) state_STOP2();
    else if ((eccState == index_HELD)) state_NEW_REQUEST();
    else if ((eccState == index_WANTED)) state_HOLD_REQUEST();
  }
  /** ALGORITHM INIT IN ST*/
public void alg_INIT(){
MotoRotate.value=true;
Block.value=false;

System.out.println(this+" "+MotoRotate.value);
System.out.println(MotoRotate.value);
}
  /** ALGORITHM REQ IN ST*/
public void alg_REQ(){
System.out.println(this+" -> Candidate"+Candidate.value);
if(Candidate.value){
if(lastPE.value!=PE.value){
if(!PE.value){
BlockCon.value=true;
System.out.println("BlockCon = true");
}
else{
BlockCon.value=false;
System.out.println("BlockCon = false");
}
lastPE.value=PE.value;
}
if(lastBlock.value!=Block.value){
if(Block.value){
STOP.serviceEvent(this);
MotoRotate.value=false;
System.out.println("Cas Stop");
}
else{
START.serviceEvent(this);
MotoRotate.value=true;
System.out.println("Cas Start");
}
lastBlock.value=Block.value;
}
}
}
  /** ALGORITHM START IN ST*/
public void alg_START(){
MotoRotate.value=true;
System.out.println(this+" Start "+MotoRotate.value);

System.out.println("Start "+MotoRotate.value);

System.out.println("ENTERING CRITICAL SECTION");
}
  /** ALGORITHM STOP IN ST*/
public void alg_STOP(){
MotoRotate.value=false;
System.out.println(this+" Stop "+MotoRotate.value);

System.out.println("Stop "+MotoRotate.value);
System.out.println("IN WAITING");
}
  /** ALGORITHM print IN ST*/
public void alg_print(){
System.out.println(this+"IM here");
}
  /** ALGORITHM START1 IN Java*/
public void alg_START1(){
System.out.println("START STATE");

}
  /** ALGORITHM SEND_P IN Java*/
public void alg_SEND_P(){
System.out.println("SEND STATE");

}
  /** ALGORITHM WANTED_P IN Java*/
public void alg_WANTED_P(){
System.out.println("WANTED STATE");

}
  /** ALGORITHM HELD_P IN Java*/
public void alg_HELD_P(){
System.out.println("HELD STATE");

}
  /** ALGORITHM NEWBAG_P IN Java*/
public void alg_NEWBAG_P(){
System.out.println("NEWBAG STATE");

}
  /** ALGORITHM NEWREQUEST_P IN Java*/
public void alg_NEWREQUEST_P(){
System.out.println("NEWREQUEST STATE");

}
  /** ALGORITHM SEND1_P IN Java*/
public void alg_SEND1_P(){
System.out.println("SEND1 STATE");

}
  /** ALGORITHM STOP_P IN Java*/
public void alg_STOP_P(){
System.out.println("STOP STATE");

}
  /** ALGORITHM HOLD_P IN Java*/
public void alg_HOLD_P(){
System.out.println("HOLD STATE");

}
  /** ALGORITHM NEW_REQUEST2_P IN Java*/
public void alg_NEW_REQUEST2_P(){
System.out.println("NEW_REQUEST2 STATE");

}
  /** ALGORITHM Plz IN ST*/
public void alg_Plz(){
StoreEmit.value=true;
}
  /** ALGORITHM not_plz IN ST*/
public void alg_not_plz(){
StoreEmit.value=false;
}
}
