/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK RingTokenCTL
  * @author JHC
  * @version 20191024/JHC
  */
public class RingTokenCTL extends FBInstance
{
/** Input event qualifier */
  public BOOL PE = new BOOL();
/** VAR Block */
  public BOOL Block = new BOOL();
/** VAR Candidate */
  public BOOL Candidate = new BOOL();
/** VAR TokenInput */
  public BOOL TokenInput = new BOOL();
/** VAR PExit */
  public BOOL PExit = new BOOL();
/** Output event qualifier */
  public BOOL MotoRotate = new BOOL();
/** VAR BlockCon */
  public BOOL BlockCon = new BOOL();
/** VAR TokenOutput */
  public BOOL TokenOutput = new BOOL();
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
/** EVENT TokenStatus_Input */
 public EventServer TokenStatus_Input = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** EVENT STOP */
 public EventOutput STOP = new EventOutput();
/** EVENT START */
 public EventOutput START = new EventOutput();
/** EVENT TokenStatus_Output */
 public EventOutput TokenStatus_Output = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("REQ".equals(s)) return REQ;
    if("CAS_STOP".equals(s)) return CAS_STOP;
    if("CAS_START".equals(s)) return CAS_START;
    if("TokenStatus_Input".equals(s)) return TokenStatus_Input;
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
    if("TokenStatus_Output".equals(s)) return TokenStatus_Output;
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
    if("TokenInput".equals(s)) return TokenInput;
    if("PExit".equals(s)) return PExit;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("MotoRotate".equals(s)) return MotoRotate;
    if("BlockCon".equals(s)) return BlockCon;
    if("TokenOutput".equals(s)) return TokenOutput;
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
    else if("TokenInput".equals(ivName)) connect_TokenInput((BOOL)newIV);
    else if("PExit".equals(ivName)) connect_PExit((BOOL)newIV);
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
/** Connect the given variable to the input variable TokenInput
  * @param newIV The variable to connect
 */
  public void connect_TokenInput(BOOL newIV){
    TokenInput = newIV;
    }
/** Connect the given variable to the input variable PExit
  * @param newIV The variable to connect
 */
  public void connect_PExit(BOOL newIV){
    PExit = newIV;
    }
private static final int index_TOKENLESS = 0;
private void state_TOKENLESS(){
  eccState = index_TOKENLESS;
  alg_TOKEN_FREE();
  TokenStatus_Output.serviceEvent(this);
  alg_START();
  START.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
  CNF.serviceEvent(this);
state_TOKENLESS();
}
private static final int index_WAIT = 2;
private void state_WAIT(){
  eccState = index_WAIT;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_TOKENFUL = 3;
private void state_TOKENFUL(){
  eccState = index_TOKENFUL;
  alg_TOKEN_IN_USE();
  TokenStatus_Output.serviceEvent(this);
}
private static final int index_CRITICAL_SECTION = 4;
private void state_CRITICAL_SECTION(){
  eccState = index_CRITICAL_SECTION;
  alg_START();
  START.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_DONE = 5;
private void state_DONE(){
  eccState = index_DONE;
}
private static final int index_DONE_STOP = 6;
private void state_DONE_STOP(){
  eccState = index_DONE_STOP;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_DONE_EXIT_WAIT = 7;
private void state_DONE_EXIT_WAIT(){
  eccState = index_DONE_EXIT_WAIT;
}
private static final int index_TOKENLESS_CASCSTOP = 8;
private void state_TOKENLESS_CASCSTOP(){
  eccState = index_TOKENLESS_CASCSTOP;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_CRITSEC_CASCSTOP = 9;
private void state_CRITSEC_CASCSTOP(){
  eccState = index_CRITSEC_CASCSTOP;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
}
private static final int index_DONE_CASCSTOP = 10;
private void state_DONE_CASCSTOP(){
  eccState = index_DONE_CASCSTOP;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
state_DONE();
}
private static final int index_DONE_CASCSTART = 11;
private void state_DONE_CASCSTART(){
  eccState = index_DONE_CASCSTART;
  alg_START();
  START.serviceEvent(this);
  CNF.serviceEvent(this);
state_DONE();
}
private static final int index_DEW_CASCSTOP = 12;
private void state_DEW_CASCSTOP(){
  eccState = index_DEW_CASCSTOP;
  alg_STOP();
  STOP.serviceEvent(this);
  CNF.serviceEvent(this);
state_DONE_EXIT_WAIT();
}
private static final int index_DEW_CASCSTART = 13;
private void state_DEW_CASCSTART(){
  eccState = index_DEW_CASCSTART;
  alg_START();
  START.serviceEvent(this);
  CNF.serviceEvent(this);
state_DONE_EXIT_WAIT();
}
/** The default constructor. */
public RingTokenCTL(){
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
    else if (e == TokenStatus_Input) service_TokenStatus_Input();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_TOKENLESS)) state_INIT();
  }
/** Services the REQ event. */
  public void service_REQ(){
    if ((eccState == index_TOKENLESS) && (!PE.value)) state_WAIT();
    else if ((eccState == index_TOKENFUL) && (!PE.value)) state_CRITICAL_SECTION();
    else if ((eccState == index_TOKENFUL) && (PE.value)) state_TOKENLESS();
    else if ((eccState == index_CRITICAL_SECTION) && (PE.value)) state_DONE();
    else if ((eccState == index_DONE) && (!PE.value)) state_DONE_STOP();
    else if ((eccState == index_DONE) && (!PExit.value)) state_DONE_EXIT_WAIT();
    else if ((eccState == index_DONE_STOP) && (!PExit.value)) state_DONE_EXIT_WAIT();
    else if ((eccState == index_DONE_EXIT_WAIT) && (PExit.value)) state_TOKENLESS();
  }
/** Services the CAS_STOP event. */
  public void service_CAS_STOP(){
    if ((eccState == index_CRITICAL_SECTION)) state_CRITSEC_CASCSTOP();
    else if ((eccState == index_DONE)) state_DONE_CASCSTOP();
    else if ((eccState == index_DONE_EXIT_WAIT)) state_DEW_CASCSTOP();
    else if ((eccState == index_TOKENLESS)) state_TOKENLESS_CASCSTOP();
  }
/** Services the CAS_START event. */
  public void service_CAS_START(){
    if ((eccState == index_CRITSEC_CASCSTOP)) state_CRITICAL_SECTION();
    else if ((eccState == index_DONE)) state_DONE_CASCSTART();
    else if ((eccState == index_DONE_EXIT_WAIT)) state_DEW_CASCSTART();
    else if ((eccState == index_TOKENLESS_CASCSTOP)) state_TOKENLESS();
  }
/** Services the TokenStatus_Input event. */
  public void service_TokenStatus_Input(){
    if ((eccState == index_WAIT) && (TokenInput.value)) state_CRITICAL_SECTION();
    else if ((eccState == index_TOKENLESS) && (TokenInput.value)) state_TOKENFUL();
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
}
  /** ALGORITHM STOP IN ST*/
public void alg_STOP(){
MotoRotate.value=false;
System.out.println(this+" Stop "+MotoRotate.value);

System.out.println("Stop "+MotoRotate.value);
}
  /** ALGORITHM TOKEN_IN_USE IN ST*/
public void alg_TOKEN_IN_USE(){
TokenOutput.value=false;
}
  /** ALGORITHM TOKEN_FREE IN ST*/
public void alg_TOKEN_FREE(){
TokenOutput.value=true;
}
}
