/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.template;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK CentralServer
  * @author JHC
  * @version 20191020/JHC
  */
public class CentralServer extends FBInstance
{
/** Input event qualifier */
  public BOOL TokIN = new BOOL();
/** Output event qualifier */
  public BOOL TokOUT = new BOOL();
/** Initialization Request */
 public EventServer Request0 = new EventInput(this);
/** dab */
 public EventServer Request1 = new EventInput(this);
/** EVENT Relinquish0 */
 public EventServer Relinquish0 = new EventInput(this);
/** EVENT Relinquish1 */
 public EventServer Relinquish1 = new EventInput(this);
/** Initialization Confirm */
 public EventOutput Grant0 = new EventOutput();
/** Execution Confirmation */
 public EventOutput Grant1 = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("Request0".equals(s)) return Request0;
    if("Request1".equals(s)) return Request1;
    if("Relinquish0".equals(s)) return Relinquish0;
    if("Relinquish1".equals(s)) return Relinquish1;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("Grant0".equals(s)) return Grant0;
    if("Grant1".equals(s)) return Grant1;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("TokIN".equals(s)) return TokIN;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("TokOUT".equals(s)) return TokOUT;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("TokIN".equals(ivName)) connect_TokIN((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable TokIN
  * @param newIV The variable to connect
 */
  public void connect_TokIN(BOOL newIV){
    TokIN = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  Grant0.serviceEvent(this);
state_START();
}
private static final int index_REQ = 2;
private void state_REQ(){
  eccState = index_REQ;
  alg_REQ();
  Grant1.serviceEvent(this);
state_START();
}
/** The default constructor. */
public CentralServer(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == Request0) service_Request0();
    else if (e == Request1) service_Request1();
    else if (e == Relinquish0) service_Relinquish0();
    else if (e == Relinquish1) service_Relinquish1();
  }
/** Services the Request0 event. */
  public void service_Request0(){
    if ((eccState == index_START)) state_INIT();
  }
/** Services the Request1 event. */
  public void service_Request1(){
    if ((eccState == index_START)) state_REQ();
  }
/** Services the Relinquish0 event. */
  public void service_Relinquish0(){
  }
/** Services the Relinquish1 event. */
  public void service_Relinquish1(){
  }
  /** ALGORITHM INIT IN ST*/
public void alg_INIT(){
}
  /** ALGORITHM REQ IN ST*/
public void alg_REQ(){
}
}
