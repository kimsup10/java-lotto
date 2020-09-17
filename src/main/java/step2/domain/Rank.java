package step2.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NOT_MATCH(0, 0);

    private int matchNumCount;
    private int prize;

    private Rank(int matchNumCount, int prize) {
        this.matchNumCount = matchNumCount;
        this.prize = prize;
    }

    public static Rank getRank(int matchNumCount) {
        return Arrays.stream(Rank.values())
                .filter((rank) -> rank.matchNumCount == matchNumCount)
                .findAny()
                .orElse(NOT_MATCH);
    }

    public static Map<Rank, Integer> getInitRankInfo() {
        return Arrays.stream(Rank.values())
                .collect(Collectors.toMap(rank -> rank, rank -> 0));
    }

    public int getPrize() {
        return this.prize;
    }
}