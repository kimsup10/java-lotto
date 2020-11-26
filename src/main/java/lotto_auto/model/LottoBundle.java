package lotto_auto.model;

import lotto_auto.ErrorMessage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class LottoBundle {

    private List<LottoTicket> lottoTicketList;

    public LottoBundle(int ticketCount) {
        throwIfNegativeTicketCount(ticketCount);
        this.lottoTicketList = Collections.unmodifiableList(
                IntStream.range(0, ticketCount)
                        .mapToObj(item -> new LottoTicket())
                        .collect(Collectors.toList())
        );
    }

    public int getTicketCount() {
        return lottoTicketList.size();
    }

    public List<LottoTicket> export() {
        return Collections.unmodifiableList(lottoTicketList);
    }

    public LottoStatistic draw(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER);
        }
        return new LottoStatistic(lottoTicketList.stream()
                .map(item -> item.draw(lottoNumbers, bonusNumber))
                .collect(Collectors.toList()),
                lottoTicketList.size() * LottoTicket.PRICE);
    }

    private void throwIfNegativeTicketCount(int ticketCount) {
        if (ticketCount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TICKET_COUNT);
        }
    }
}