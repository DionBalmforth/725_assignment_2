/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK TwoConCtlServer
  * @author JHC
  * @version 20191024/JHC
  */
public class TwoConCtlServer extends FBInstance
{
/** VAR Candidate */
  public BOOL Candidate = new BOOL();
/** VAR Block */
  public BOOL Block = new BOOL();
/** VAR EnterPE */
  public BOOL EnterPE = new BOOL();
/** VAR ExitPE */
  public BOOL ExitPE = new BOOL();
/** VAR MotoRotate1 */
  public BOOL MotoRotate1 = new BOOL();
/** VAR MotoRotate2 */
  public BOOL MotoRotate2 = new BOOL();
/** VAR BlockCon */
  public BOOL BlockCon = new BOOL();
/** Initialization Request */
 public EventOutput INIT = new EventOutput();
/** Normal Execution Request */
 public EventOutput REQ = new EventOutput();
/** EVENT START */
 public EventOutput START = new EventOutput();
/** EVENT STOP */
 public EventOutput STOP = new EventOutput();
/** EVENT Grant */
 public EventOutput Grant = new EventOutput();
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
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
    if("START".equals(s)) return START;
    if("STOP".equals(s)) return STOP;
    if("Grant".equals(s)) return Grant;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("CNF".equals(s)) return CNF;
    if("Request".equals(s)) return Request;
    if("Release".equals(s)) return Release;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("Candidate".equals(s)) return Candidate;
    if("Block".equals(s)) return Block;
    if("EnterPE".equals(s)) return EnterPE;
    if("ExitPE".equals(s)) return ExitPE;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("MotoRotate1".equals(s)) return MotoRotate1;
    if("MotoRotate2".equals(s)) return MotoRotate2;
    if("BlockCon".equals(s)) return BlockCon;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("Candidate".equals(ivName)) connect_Candidate((BOOL)newIV);
    else if("Block".equals(ivName)) connect_Block((BOOL)newIV);
    else if("EnterPE".equals(ivName)) connect_EnterPE((BOOL)newIV);
    else if("ExitPE".equals(ivName)) connect_ExitPE((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable Candidate
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_Candidate(BOOL newIV) throws FBRManagementException{
    Candidate = newIV;
    FC12.connectIVNoException("Candidate",Candidate);
    }
/** Connect the given variable to the input variable Block
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_Block(BOOL newIV) throws FBRManagementException{
    Block = newIV;
    FC12.connectIVNoException("Block",Block);
    }
/** Connect the given variable to the input variable EnterPE
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_EnterPE(BOOL newIV) throws FBRManagementException{
    EnterPE = newIV;
    FC12.connectIVNoException("EnterPE",EnterPE);
    }
/** Connect the given variable to the input variable ExitPE
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_ExitPE(BOOL newIV) throws FBRManagementException{
    ExitPE = newIV;
    FC12.connectIVNoException("ExitPE",ExitPE);
    }
/** FB FC11 */
  protected ConveyorCTL FC11 = new ConveyorCTL() ;
/** FB FC12 */
  protected ConveyorCTLServer FC12 = new ConveyorCTLServer() ;
/** The default constructor. */
public TwoConCtlServer(){
    super();
    INIT.connectTo(FC11.INIT);
    REQ.connectTo(FC11.REQ);
    FC12.INITO.connectTo(INITO);
    FC12.CNF.connectTo(CNF);
    FC12.Request.connectTo(Request);
    FC12.Release.connectTo(Release);
    REQ.connectTo(FC12.REQ);
    STOP.connectTo(FC12.CAS_STOP);
    START.connectTo(FC12.CAS_START);
    Grant.connectTo(FC12.Grant);
    FC12.STOP.connectTo(FC11.CAS_STOP);
    FC12.START.connectTo(FC11.CAS_START);
    FC11.INITO.connectTo(FC12.INIT);
    FC12.connectIVNoException("Block",Block);
    FC12.connectIVNoException("Candidate",Candidate);
    FC12.connectIVNoException("EnterPE",EnterPE);
    FC12.connectIVNoException("ExitPE",ExitPE);
    BlockCon = (BOOL)FC12.ovNamedNoException("BlockCon");
    MotoRotate1 = (BOOL)FC11.ovNamedNoException("MotoRotate");
    MotoRotate2 = (BOOL)FC12.ovNamedNoException("MotoRotate");
    FC11.PE.initializeNoException("0");
    FC11.Block.initializeNoException("0");
    FC11.Candidate.initializeNoException("0");
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
}
/** Start the FB instances. */
public void start( ){
  FC11.start();
  FC12.start();
}
/** Stop the FB instances. */
public void stop( ){
  FC11.stop();
  FC12.stop();
}
}
