package com.lohika.example.flightreservation;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import RmiFlights.FDate;
import RmiFlights.FFlightsScheduler;
import RmiFlights.FLogin;
import RmiFlights.FOrderInformation;
import RmiFlights.FlyghtClass;

public class FlightReservationEndToEnd {

	java.lang.Object[] _object_array1;
	java.util.Properties _properties1;
	RmiFlights.ServerInterface _serverinterface1;
	RmiFlights.LogicFlightServer _logicflightserver1;
	java.lang.String[] _string_array1;
	RmiFlights.FLogin _flogin1;
	java.lang.String _string1;
	java.lang.String[] _string_array2;
	java.lang.String[] _string_array3;
	RmiFlights.FFlightsScheduler _fflightsscheduler1;
	java.lang.String _string2;
	RmiFlights.FlightTable[] _flighttable_array1;
	RmiFlights.FOrderInformation _forderinformation1;
	java.lang.String _string3;
	RmiFlights.ReservedOrder _reservedorder1;
	java.lang.String _string4;
	RmiFlights.ReservedOrder _reservedorder2;
	java.lang.String _string5;
	RmiFlights.ReservedOrder _reservedorder3;

	@Before
	public void setUp() {
		// Set system properties...
		_properties1 = System.getProperties();
		_properties1.put("sun.desktop", "windows");
		_properties1.put("sun.jnu.encoding", "Cp1252");
		_properties1.put("sun.awt.enableExtraMouseButtons", "true");
		_properties1.put("sun.management.compiler", "HotSpot Client Compiler");
		_properties1.put("sun.java.launcher", "SUN_STANDARD");
		_properties1.put("user.script", "");
		_properties1.put("sun.java.command", "RmiFlights.main");
		System.setProperties(_properties1);

		// Installing RMISecurityManager
		/*if (System.getSecurityManager() == null)
			System.setSecurityManager(new java.rmi.RMISecurityManager());*/

		// Lookup a remote object...
		try {
			_serverinterface1 = (RmiFlights.ServerInterface) java.rmi.Naming
					.lookup("rmi://localhost/FlightServer");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void EndToEnd() {
		try {
			_logicflightserver1 = _serverinterface1.getAppServer();

			_string_array1 = _logicflightserver1.getAgentNames();

			_flogin1 = new FLogin("Alex", "mercury");

			_logicflightserver1.checkLogin((RmiFlights.FLogin) _flogin1);

			_string_array2 = _logicflightserver1.getFlightCities(1);

			_string_array3 = _logicflightserver1.getFlightCities(2);

			_fflightsscheduler1 = new FFlightsScheduler(new FDate(15, 7, 2015),
					"Denver", "Los Angeles");
			_flighttable_array1 = _logicflightserver1
					.getOrderInformation((RmiFlights.FFlightsScheduler) _fflightsscheduler1);

			_forderinformation1 = new FOrderInformation(new FDate(15, 7, 2015),
					"qatest1", 2, 119, "238", FlyghtClass.Economy, 0, 1753,
					"NW", "02:57 PM", "03:55 PM");

			_reservedorder1 = _logicflightserver1.insertOrder(
					(RmiFlights.FOrderInformation) _forderinformation1,
					(RmiFlights.FLogin) _flogin1);

			_forderinformation1.numberOfTickets = 3;
			_reservedorder2 = _logicflightserver1.updateOrder(
					(RmiFlights.FOrderInformation) _forderinformation1,
					(RmiFlights.FLogin) _flogin1,
					(RmiFlights.ReservedOrder) _reservedorder1);

			_reservedorder3 = _logicflightserver1.deleteOrder(
					(RmiFlights.FOrderInformation) _forderinformation1,
					(RmiFlights.FLogin) _flogin1,
					(RmiFlights.ReservedOrder) _reservedorder2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
