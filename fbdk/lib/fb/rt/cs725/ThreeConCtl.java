/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK ThreeConCtl
  * @author JHC
  * @version 20191024/JHC
  */
public class ThreeConCtl extends FBInstance
{
/** VAR Candidate */
  public BOOL Candidate = new BOOL();
/** VAR Block */
  public BOOL Block = new BOOL();
/** VAR PE */
  public BOOL PE = new BOOL();
/** VAR PE1 */
  public BOOL PE1 = new BOOL();
/** VAR PE2 */
  public BOOL PE2 = new BOOL();
/** VAR PE14 */
  public BOOL PE14 = new BOOL();
/** VAR TokenInput */
  public BOOL TokenInput = new BOOL();
/** VAR PExit */
  public BOOL PExit = new BOOL();
/** VAR MotoRotate1 */
  public BOOL MotoRotate1 = new BOOL();
/** VAR MotoRotate2 */
  public BOOL MotoRotate2 = new BOOL();
/** VAR MotoRotate3 */
  public BOOL MotoRotate3 = new BOOL();
/** VAR BlockCon */
  public BOOL BlockCon = new BOOL();
/** VAR TokenOutput */
  public BOOL TokenOutput = new BOOL();
/** Initialization Request */
 public EventOutput INIT = new EventOutput();
/** Normal Execution Request */
 public EventOutput REQ = new EventOutput();
/** EVENT REPLY_IN */
 public EventOutput REPLY_IN = new EventOutput();
/** EVENT REQUEST_IN */
 public EventOutput REQUEST_IN = new EventOutput();
/** EVENT TokenStatus_Input */
 public EventOutput TokenStatus_Input = new EventOutput();
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** EVENT START */
 public EventOutput START = new EventOutput();
/** EVENT STOP */
 public EventOutput STOP = new EventOutput();
/** EVENT REPLY_OUT */
 public EventOutput REPLY_OUT = new EventOutput();
/** EVENT REQUEST_OUT */
 public EventOutput REQUEST_OUT = new EventOutput();
/** EVENT TokenStatus_Output */
 public EventOutput TokenStatus_Output = new EventOutput();
/** EVENT Cascade_Stop */
 public EventOutput Cascade_Stop = new EventOutput();
/** EVENT Cascade_Start */
 public EventOutput Cascade_Start = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("REQ".equals(s)) return REQ;
    if("REPLY_IN".equals(s)) return REPLY_IN;
    if("REQUEST_IN".equals(s)) return REQUEST_IN;
    if("TokenStatus_Input".equals(s)) return TokenStatus_Input;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("CNF".equals(s)) return CNF;
    if("START".equals(s)) return START;
    if("STOP".equals(s)) return STOP;
    if("REPLY_OUT".equals(s)) return REPLY_OUT;
    if("REQUEST_OUT".equals(s)) return REQUEST_OUT;
    if("TokenStatus_Output".equals(s)) return TokenStatus_Output;
    if("Cascade_Stop".equals(s)) return Cascade_Stop;
    if("Cascade_Start".equals(s)) return Cascade_Start;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("Candidate".equals(s)) return Candidate;
    if("Block".equals(s)) return Block;
    if("PE".equals(s)) return PE;
    if("PE1".equals(s)) return PE1;
    if("PE2".equals(s)) return PE2;
    if("PE14".equals(s)) return PE14;
    if("TokenInput".equals(s)) return TokenInput;
    if("PExit".equals(s)) return PExit;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("MotoRotate1".equals(s)) return MotoRotate1;
    if("MotoRotate2".equals(s)) return MotoRotate2;
    if("MotoRotate3".equals(s)) return MotoRotate3;
    if("BlockCon".equals(s)) return BlockCon;
    if("TokenOutput".equals(s)) return TokenOutput;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("Candidate".equals(ivName)) connect_Candidate((BOOL)newIV);
    else if("Block".equals(ivName)) connect_Block((BOOL)newIV);
    else if("PE".equals(ivName)) connect_PE((BOOL)newIV);
    else if("PE1".equals(ivName)) connect_PE1((BOOL)newIV);
    else if("PE2".equals(ivName)) connect_PE2((BOOL)newIV);
    else if("PE14".equals(ivName)) connect_PE14((BOOL)newIV);
    else if("TokenInput".equals(ivName)) connect_TokenInput((BOOL)newIV);
    else if("PExit".equals(ivName)) connect_PExit((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable Candidate
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_Candidate(BOOL newIV) throws FBRManagementException{
    Candidate = newIV;
    FC11.connectIVNoException("Candidate",Candidate);
    }
/** Connect the given variable to the input variable Block
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_Block(BOOL newIV) throws FBRManagementException{
    Block = newIV;
    FC11.connectIVNoException("Block",Block);
    }
/** Connect the given variable to the input variable PE
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE(BOOL newIV) throws FBRManagementException{
    PE = newIV;
    FC11.connectIVNoException("PE",PE);
    }
/** Connect the given variable to the input variable PE1
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE1(BOOL newIV) throws FBRManagementException{
    PE1 = newIV;
    FC12.connectIVNoException("PE",PE1);
    }
/** Connect the given variable to the input variable PE2
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE2(BOOL newIV) throws FBRManagementException{
    PE2 = newIV;
    FC13.connectIVNoException("PE",PE2);
    }
/** Connect the given variable to the input variable PE14
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE14(BOOL newIV) throws FBRManagementException{
    PE14 = newIV;
    FC12.connectIVNoException("PE14",PE14);
    }
/** Connect the given variable to the input variable TokenInput
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_TokenInput(BOOL newIV) throws FBRManagementException{
    TokenInput = newIV;
    FC11.connectIVNoException("TokenInput",TokenInput);
    }
/** Connect the given variable to the input variable PExit
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PExit(BOOL newIV) throws FBRManagementException{
    PExit = newIV;
    FC11.connectIVNoException("PExit",PExit);
    }
/** FB FC11 */
  protected RingTokenCTL FC11 = new RingTokenCTL() ;
/** FB FC12 */
  protected ConveyorCTLMulti FC12 = new ConveyorCTLMulti() ;
/** FB FC13 */
  protected ConveyorCTL FC13 = new ConveyorCTL() ;
/** The default constructor. */
public ThreeConCtl(){
    super();
    INIT.connectTo(FC11.INIT);
    REQ.connectTo(FC11.REQ);
    FC11.INITO.connectTo(FC12.INIT);
    FC12.INITO.connectTo(FC13.INIT);
    FC13.INITO.connectTo(INITO);
    FC13.CNF.connectTo(CNF);
    REQ.connectTo(FC12.REQ);
    REQ.connectTo(FC13.REQ);
    FC11.CNF.connectTo(CNF);
    FC12.CNF.connectTo(CNF);
    REPLY_IN.connectTo(FC12.REPLY_IN);
    REQUEST_IN.connectTo(FC12.REQUEST_IN);
    FC12.REQUEST_OUT.connectTo(REQUEST_OUT);
    FC12.REPLY_OUT.connectTo(REPLY_OUT);
    FC11.TokenStatus_Output.connectTo(TokenStatus_Output);
    TokenStatus_Input.connectTo(FC11.TokenStatus_Input);
    FC12.STOP.connectTo(FC11.CAS_STOP);
    FC12.START.connectTo(FC11.CAS_START);
    FC11.STOP.connectTo(STOP);
    FC11.START.connectTo(START);
    FC12.STOP.connectTo(Cascade_Stop);
    FC12.START.connectTo(Cascade_Start);
    MotoRotate3 = (BOOL)FC13.ovNamedNoException("MotoRotate");
    MotoRotate2 = (BOOL)FC12.ovNamedNoException("MotoRotate");
    MotoRotate1 = (BOOL)FC11.ovNamedNoException("MotoRotate");
    FC11.connectIVNoException("PE",PE);
    FC12.connectIVNoException("PE",PE1);
    FC13.connectIVNoException("PE",PE2);
    FC11.connectIVNoException("Block",Block);
    BlockCon = (BOOL)FC11.ovNamedNoException("BlockCon");
    FC11.connectIVNoException("Candidate",Candidate);
    FC12.connectIVNoException("PE14",PE14);
    FC11.connectIVNoException("PExit",PExit);
    FC11.connectIVNoException("TokenInput",TokenInput);
    TokenOutput = (BOOL)FC11.ovNamedNoException("TokenOutput");
    FC12.Block.initializeNoException("0");
    FC12.Candidate.initializeNoException("0");
    FC13.Block.initializeNoException("0");
    FC13.Candidate.initializeNoException("0");
  }
/** {@inheritDoc}
 * @param fbName {@inheritDoc}
 * @param r {@inheritDoc}
 * @throws FBRManagementException {@inheritDoc} */
  public void initialize(String fbName, Resource r)
  throws FBRManagementException{
    super.initialize(fbName,r);
    FC11.initialize("FC11",r);
    FC12.initialize("FC12",r);
    FC13.initialize("FC13",r);
}
/** Start the FB instances. */
public void start( ){
  FC11.start();
  FC12.start();
  FC13.start();
}
/** Stop the FB instances. */
public void stop( ){
  FC11.stop();
  FC12.stop();
  FC13.stop();
}
}
