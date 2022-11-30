package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.MockHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmTest {

    Alarm alarm;
    Sensor sensor;
    @Before
    public void prepare(){
        sensor = mock(Sensor.class);
        alarm = new Alarm(sensor);
    }

    @Test
    public void testAlarmShouldBeOnBecauseLowPressure(){

        when(sensor.popNextPressurePsiValue()).thenReturn(14.0);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());

    }

    @Test
    public void testAlarmShouldBeOnBecauseHighPressure(){

        when(sensor.popNextPressurePsiValue()).thenReturn(25.0);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());

    }

    @Test
    public void testAlarmShouldBeOnBeOff(){

        when(sensor.popNextPressurePsiValue()).thenReturn(20.0);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());

    }

}