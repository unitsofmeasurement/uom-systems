package systems.uom.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.measure.IncommensurableException;
import javax.measure.UnconvertibleException;
import javax.measure.quantity.Length;

import org.junit.Test;

import systems.uom.quantity.Information;
import systems.uom.quantity.Resolution;
import tec.uom.se.ComparableQuantity;
import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.MetricPrefix;
import tec.uom.se.unit.Units;

public class PixelTest {

	@Test
	public void testPPItoMetreConversion() throws UnconvertibleException, IncommensurableException {
		/*
		 * 960 Pixel are .254 m long if the PPI is 96. 1 inch (0.0254 m) / 96 ppi x (96 x 10) px = 0.254 m
		 */
		final ComparableQuantity<Information> screenWidth = Quantities.getQuantity(960, NonSI.PIXEL);
		final ComparableQuantity<Resolution> screenResolution = Quantities.getQuantity(96, NonSI.PIXEL_PER_INCH);
		
		ComparableQuantity<Length> ppi = screenWidth.divide(screenResolution).asType(Length.class);
		ComparableQuantity<Length> pixelToMetre = ppi.to(Units.METRE);
		assertThat(pixelToMetre.getValue().doubleValue(),is(.254));
		assertThat(pixelToMetre.to(MetricPrefix.CENTI(Units.METRE)).getValue().doubleValue(), is(25.4));
	}
}
