package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	private Lottos lottos;
	private List<List<Integer>> inputNumbers;

	@BeforeEach
	void setUp() {
		inputNumbers = new ArrayList<>();
		inputNumbers.add(Arrays.asList(11, 12, 13, 14, 15, 16));
		inputNumbers.add(Arrays.asList(21, 22, 23, 24, 15, 16));
		inputNumbers.add(Arrays.asList(31, 32, 33, 14, 35, 16));
		inputNumbers.add(Arrays.asList(31, 32, 33, 14, 35, 16));
		inputNumbers.add(Arrays.asList(31, 32, 33, 14, 35, 16));
		inputNumbers.add(Arrays.asList(31, 32, 33, 14, 35, 16));
		inputNumbers.add(Arrays.asList(1, 2, 3, 14, 15, 16));

		List<Lotto> lottoList = new ArrayList<>();
		for (List<Integer> numbers : inputNumbers) {
			lottoList.add(new Lotto(numbers));
		}
		lottos = new Lottos(lottoList);
	}

	@Test
	@DisplayName("생성")
	void create() {
		assertThat(lottos).isNotNull();
	}
	
	@Test
	@DisplayName("매칭 (정렬 포함)")
	void matching() {
		List<List<Integer>> allNumbers = lottos.allNumbers();
		for (int i = 0; i < allNumbers.size(); i++) {
			Collections.sort(inputNumbers.get(i));
			assertThat(inputNumbers.get(i)).containsSequence(allNumbers.get(i));
		}
	}

	@Test
	@DisplayName("로또 추가")
	void addLotto() {
		List<List<Integer>> newNumbers = new ArrayList<>();
		newNumbers.add(Arrays.asList(12, 18, 22, 26, 36, 37));
		newNumbers.add(Arrays.asList(5, 22, 31, 32, 36, 37));
		newNumbers.add(Arrays.asList(13, 24, 26, 33, 41, 42));

		List<Lotto> newLottos = new ArrayList<>();
		for (List<Integer> numbers : newNumbers) {
			newLottos.add(new Lotto(numbers));
		}
		lottos.add(new Lottos(newLottos));

		newNumbers.addAll(inputNumbers);
		assertThat(lottos.allNumbers()).containsAnyElementsOf(newNumbers);
	}
}
