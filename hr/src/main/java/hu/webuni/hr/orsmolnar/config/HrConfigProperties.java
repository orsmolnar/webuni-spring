package hu.webuni.hr.orsmolnar.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {
	
	private Payraise payraise = new Payraise();
	
	public Payraise getPayraise() {
		return payraise;
	}

	public void setPayraise(Payraise payraise) {
		this.payraise= payraise;
	}
	
	public static class Payraise {
		private double defaultPercent;
		private List<Range> smartRanges = new ArrayList<>();

		public double getDefaultPercent() {
			return defaultPercent;
		}
		public void setDefaultPercent(double defaultPercent) {
			this.defaultPercent = defaultPercent;
		}
		
		public List<Range> getSmartRanges() {
			return smartRanges;
		}
		public void setSmartRanges(List<Range> smartRanges) {
			this.smartRanges = smartRanges;
		}

		public static class Range {
			private double percent;
			private int yearsOfTerm;
			private int monthsOfterm;
		
			public double getPercent() {
				return percent;
			}
			public void setPercent(double percent) {
				this.percent = percent;
			}
			public int getYearsOfTerm() {
				return yearsOfTerm;
			}
			public void setYearsOfTerm(int yearsOfTerm) {
				this.yearsOfTerm = yearsOfTerm;
			}	
			public int getMonthsOfTerm() {
				return monthsOfterm;
			}
			public void setMonthsOfTerm(int monthsOfterm) {
				this.monthsOfterm = monthsOfterm;
			}	
		}
	}
}
