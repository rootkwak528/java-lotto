package calculator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringAddCalculatorTest {

	@Test
	@DisplayName("빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다")
	public void splitAndSum_null_또는_빈문자() {
		verifyResult(null, 0);

		verifyResult("", 0);
	}

	@Test
	@DisplayName("숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.")
	public void splitAndSum_숫자하나() throws Exception {
		verifyResult("1", 1);
	}

	@Test
	@DisplayName("숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫나의 합을 반환한다.")
	public void splitAndSum_쉼표구분자() throws Exception {
		verifyResult("1,2", 3);
	}

	@Test
	@DisplayName("구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다.")
	public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
		verifyResult("1,2:3", 6);
	}

	@Test
	@DisplayName("(//) 와 (\n) 사이에 커스텀 구분자를 지정할 수 있다.")
	public void splitAndSum_custom_구분자() throws Exception {
		verifyResult("//;\n1;2;3", 6);
	}

	@Test
	@DisplayName("음수를 전달할 경우 RuntimeException 이 발생해야 한다.")
	public void splitAndSum_negative() throws Exception {
		assertThatThrownBy(() -> StringAddCalculator.splitAndSum("-1,2,3"))
			.isInstanceOf(RuntimeException.class);
	}

	private void verifyResult(String input, int expected) {
		int result = StringAddCalculator.splitAndSum(input);
		assertThat(result).isEqualTo(expected);
	}

}