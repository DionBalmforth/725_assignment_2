/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK ConveyorCTLServer
  * @author JHC
  * @version 20191024/JHC
  */
public class ConveyorCTLServer extends FBInstance
{
/** Input event qualifier */
  public BOOL EnterPE = new BOOL();
/** VAR Block */
  public BOOL Block = new BOOL();
/** VAR Candidate */
  public BOOL Candidate = new BOOL();
/** VAR ExitPE */
  public BOOL ExitPE = new BOOL();
/** Output event qualifier */
  public BOOL MotoRotate = new BOOL();
/** VAR BlockCon */
  public BOOL BlockCon = new BOOL();
/** VAR lastPE */
  public BOOL lastPE = new BOOL();
/** VAR lastBlock */
  public BOOL lastBlock = new BOOL();
/** Initialization Request */
 public EventServer INIT = new EventInput(this);
/** Normal Execution Request */
 public EventServer REQ = new EventInput(this);
/** EVENT CAS_STOP */
 public EventServer CAS_STOP = new EventInput(this);
/** EVENT CAS_START */
 public EventServer CAS_START = new EventInput(this);
/** EVENT Grant */
 public EventServer Grant = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** EVENT STOP */
 public EventOutput STOP = new EventOutput();
/** EVENT START */
 public EventOutput START = new EventOutput();
/** EVENT Request */
 public EventOutput Request = new EventOutput();
/** EVENT Release */
 public EventOutput Release = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("REQ".equals(s)) return REQ;
    if("CAS_STOP".equals(s)) return CAS_STOP;
    if("CAS_START".equals(s)) return CAS_START;
    if("Grant".equals(s)) return Grant;
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
    if("Request".equals(s)) return Request;
    if("Release".equals(s)) return Release;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("EnterPE".equals(s)) return EnterPE;
    if("Block".equals(s)) return Block;
    if("Candidate".equals(s)) return Candidate;
    if("ExitPE".equals(s)) return ExitPE;
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
    if("EnterPE".equals(ivName)) connect_EnterPE((BOOL)newIV);
    else if("Block".equals(ivName)) connect_Block((BOOL)newIV);
    else if("Candidate".equals(ivName)) connect_Candidate((BOOL)newIV);
    else if("ExitPE".equals(ivName)) connect_ExitPE((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable EnterPE
  * @param newIV The variable to connect
 */
  public void connect_EnterPE(BOOL newIV){
    EnterPE = newIV;
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
/** Connect the given variable to the input variable ExitPE
  * @param newIV The variable to connect
 */
  public void connect_ExitPE(BOOL newIV){
    ExitPE = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
  CNF.serviceEvent(this);
state_START();
}
private static final int index_REQ = 2;
private void state_REQ(){
  eccState = index_REQ;
  alg_REQ();
  CNF.serviceEvent(this);
state_START();
}
private static final int index_CAS_START = 3;
private void state_CAS_START(){
  eccState = index_CAS_START;
  alg_START();
  START.serviceEvent(this);
  CNF.serviceEvent(this);
state_START();
}
private static final int index_CAS_STOP = 4;
private void state_CAS_STOP(){
  eccState = index_CAS_STOP;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
state_START();
}
private static final int index_REQUEST = 5;
private void state_REQUEST(){
  eccState = index_REQUEST;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
  alg_REQUEST();
  Request.serviceEvent(this);
}
private static final int index_EXCLUSION = 6;
private void state_EXCLUSION(){
  eccState = index_EXCLUSION;
  alg_START();
  START.serviceEvent(this);
  alg_EXCLUSION();
  CNF.serviceEvent(this);
}
private static final int index_RELEASE = 7;
private void state_RELEASE(){
  eccState = index_RELEASE;
  alg_RELEASE();
  Release.serviceEvent(this);
state_START();
}
private static final int index_HOLD = 8;
private void state_HOLD(){
  eccState = index_HOLD;
}
private static final int index_MULTIREQUEST = 9;
private void state_MULTIREQUEST(){
  eccState = index_MULTIREQUEST;
  alg_STOP();
  STOP.serviceEvent(this);
  alg_MULTIREQUEST();
}
private static final int index_MULTIRELEASE = 10;
private void state_MULTIRELEASE(){
  eccState = index_MULTIRELEASE;
  alg_MULTIRELEASE();
  Release.serviceEvent(this);
state_REQUEST();
}
private static final int index_RELEASEINTERMEDIATE = 11;
private void state_RELEASEINTERMEDIATE(){
  eccState = index_RELEASEINTERMEDIATE;
}
private static final int index_MULTIREQUESTINTERMEDIATE = 12;
private void state_MULTIREQUESTINTERMEDIATE(){
  eccState = index_MULTIREQUESTINTERMEDIATE;
}
/** The default constructor. */
public ConveyorCTLServer(){
    super();
    lastPE.initializeNoException("1");
    lastBlock.initializeNoException("0");
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
    else if (e == REQ) service_REQ();
    else if (e == CAS_STOP) service_CAS_STOP();
    else if (e == CAS_START) service_CAS_START();
    else if (e == Grant) service_Grant();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_START)) state_INIT();
  }
/** Services the REQ event. */
  public void service_REQ(){
    if ((eccState == index_START) && (Candidate.value)) state_REQ();
    else if ((eccState == index_HOLD) && (!ExitPE.value)) state_RELEASEINTERMEDIATE();
    else if ((eccState == index_START) && (!EnterPE.value)) state_REQUEST();
    else if ((eccState == index_EXCLUSION) && (EnterPE.value)) state_HOLD();
    else if ((eccState == index_HOLD) && (!EnterPE.value)) state_MULTIREQUEST();
    else if ((eccState == index_MULTIREQUEST) && (!ExitPE.value)) state_MULTIREQUESTINTERMEDIATE();
    else if ((eccState == index_RELEASEINTERMEDIATE) && (ExitPE.value)) state_RELEASE();
    else if ((eccState == index_MULTIREQUESTINTERMEDIATE) && (ExitPE.value)) state_MULTIRELEASE();
  }
/** Services the CAS_STOP event. */
  public void service_CAS_STOP(){
    if ((eccState == index_START)) state_CAS_STOP();
  }
/** Services the CAS_START event. */
  public void service_CAS_START(){
    if ((eccState == index_START)) state_CAS_START();
  }
/** Services the Grant event. */
  public void service_Grant(){
    if ((eccState == index_REQUEST)) state_EXCLUSION();
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
if(lastPE.value!=EnterPE.value){
if(!EnterPE.value){
BlockCon.value=true;
System.out.println("BlockCon = true");
}
else{
BlockCon.value=false;
System.out.println("BlockCon = false");
}
lastPE.value=EnterPE.value;
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
}
  /** ALGORITHM STOP IN Java*/
public void alg_STOP(){
MotoRotate.value=false;
System.out.println(this+" Stop "+MotoRotate.value);

System.out.println("Stop "+MotoRotate.value);

}
  /** ALGORITHM REQUEST IN Java*/
public void alg_REQUEST(){
System.out.println("Entered Request state: " + this);

}
  /** ALGORITHM EXCLUSION IN Java*/
public void alg_EXCLUSION(){
System.out.println("Entered Exclusion state");

}
  /** ALGORITHM MULTIREQUEST IN Java*/
public void alg_MULTIREQUEST(){
System.out.println("Entered Multirequest state");

}
  /** ALGORITHM MULTIRELEASE IN Java*/
public void alg_MULTIRELEASE(){
System.out.println("Entered multirelease state");

}
  /** ALGORITHM RELEASE IN Java*/
public void alg_RELEASE(){
System.out.println("Entered Release state");

}
}
