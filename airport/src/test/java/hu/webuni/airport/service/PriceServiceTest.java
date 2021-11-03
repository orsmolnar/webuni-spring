package hu.webuni.airport.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
	
	@InjectMocks
	PriceService priceService;
	
	@Mock
	DiscountService discountService;

	@Test
	void testGetFinalPrice() throws Exception {
		//példányosítok egy PriceService-t és kézzel beadok neki egy DiscountService implementációt - bármire 5-öt ad válaszul
		int newPrice = new PriceService(p -> 5).getFinalPrice(100);

		//		assertEquals(95, newPrice);		
		assertThat(newPrice).isEqualTo(95);
	}
	
	@Test
	void testGetFinalPriceWithMock() throws Exception {
		//most mockito-val!!!
		
		//felveszem a mock tagváltozókat, (a Mockito library-t statikusan importálom be), majd betanítom a mockokat:
		when(discountService.getDiscountPercent(100)).thenReturn(5);
		
		int newPrice = priceService.getFinalPrice(100);	
		assertThat(newPrice).isEqualTo(95);
	}
}
