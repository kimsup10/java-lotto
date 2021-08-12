package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotto.service.Rank;

public class Lotteries {

  public static final int COUNT = 1;

  private List<Lotto> lottos = new ArrayList<>();

  public Lotteries() {
  }

  public Lotteries(final List<Lotto> lottos) {
    this.lottos = Collections.unmodifiableList(lottos);
  }

  public static Map<Rank, Integer> MatchLottosForRank(final Lotteries lotteries, final WinLottoInfo winLottoInfo, final Map<Rank, Integer> result) {
    lotteries.lottos.forEach(
        lotto -> result.computeIfPresent(winLottoInfo.getMatchCountForRank(lotto),
            (rank, integer) -> integer + COUNT));
    return result;
  }

  public static void toStringLottoInfo(final Lotteries lotteries) {
    lotteries.lottos.stream().map(Lotto::toString).forEach(System.out::println);
  }

}