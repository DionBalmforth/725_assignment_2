/* Copyright (c)2019 Rockwell Automation. All rights reserved. */
package fb.rt.template;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK CentralServer
  * @author JHC
  * @version 20191024/JHC
  */
public class CentralServer extends FBInstance
{
/** Input event qualifier */
  public BOOL InToken0 = new BOOL();
/** Output event qualifier */
  public BOOL OutToken0 = new BOOL();
/** Initialization Request */
 public EventServer Request0 = new EventInput(this);
/** dab */
 public EventServer Request1 = new EventInput(this);
/** EVENT Release0 */
 public EventServer Release0 = new EventInput(this);
/** EVENT Release1 */
 public EventServer Release1 = new EventInput(this);
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
    if("Release0".equals(s)) return Release0;
    if("Release1".equals(s)) return Release1;
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
    if("InToken0".equals(s)) return InToken0;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("OutToken0".equals(s)) return OutToken0;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("InToken0".equals(ivName)) connect_InToken0((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable InToken0
  * @param newIV The variable to connect
 */
  public void connect_InToken0(BOOL newIV){
    InToken0 = newIV;
    }
private static final int index_NOREQUEST = 0;
private void state_NOREQUEST(){
  eccState = index_NOREQUEST;
  alg_NOREQUEST();
}
private static final int index_GRANT0 = 1;
private void state_GRANT0(){
  eccState = index_GRANT0;
  alg_GRANT0();
  Grant0.serviceEvent(this);
}
private static final int index_GRANT1 = 2;
private void state_GRANT1(){
  eccState = index_GRANT1;
  alg_GRANT1();
  Grant1.serviceEvent(this);
}
private static final int index_AWAIT0 = 3;
private void state_AWAIT0(){
  eccState = index_AWAIT0;
  alg_AWAIT0();
}
private static final int index_AWAIT1 = 4;
private void state_AWAIT1(){
  eccState = index_AWAIT1;
  alg_AWAIT1();
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
    else if (e == Release0) service_Release0();
    else if (e == Release1) service_Release1();
  }
/** Services the Request0 event. */
  public void service_Request0(){
    if ((eccState == index_NOREQUEST)) state_GRANT0();
    else if ((eccState == index_GRANT1)) state_AWAIT1();
  }
/** Services the Request1 event. */
  public void service_Request1(){
    if ((eccState == index_NOREQUEST)) state_GRANT1();
    else if ((eccState == index_GRANT0)) state_AWAIT0();
  }
/** Services the Release0 event. */
  public void service_Release0(){
    if ((eccState == index_GRANT0)) state_NOREQUEST();
    else if ((eccState == index_AWAIT0)) state_GRANT1();
  }
/** Services the Release1 event. */
  public void service_Release1(){
    if ((eccState == index_GRANT1)) state_NOREQUEST();
    else if ((eccState == index_AWAIT1)) state_GRANT0();
  }
  /** ALGORITHM GRANT0 IN Java*/
public void alg_GRANT0(){
System.out.println("Token Granted to 0");

}
  /** ALGORITHM GRANT1 IN Java*/
public void alg_GRANT1(){
System.out.println("Token Granted to 1");

}
  /** ALGORITHM NOREQUEST IN Java*/
public void alg_NOREQUEST(){
System.out.println("No request");

}
  /** ALGORITHM AWAIT0 IN Java*/
public void alg_AWAIT0(){
System.out.println("Awaiting 0");

}
  /** ALGORITHM AWAIT1 IN Java*/
public void alg_AWAIT1(){
System.out.println("Await 1");

}
}
