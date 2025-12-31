package com.dansmultipro.train.service.impl;

import com.dansmultipro.train.model.Railcar;
import com.dansmultipro.train.model.SeatRow;
import com.dansmultipro.train.model.Train;
import com.dansmultipro.train.service.TrainService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TrainServiceImpl implements TrainService {

    @Override
    public List<Train> getScedhule() {
        return Arrays.asList(
                new Train("Sancaka", "Yogyakarta", "Surabaya", LocalDateTime.now(), Arrays.asList(
                        new Railcar("Ekonomi 1", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        )),
                        new Railcar("Ekonomi 2", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        ))
                )),
                new Train("Malioboro Express", "Purwokerto", "Malang", LocalDateTime.now(), Arrays.asList(
                        new Railcar("Eksekutif 1", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        )),
                        new Railcar("Eksekutif 2", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        ))
                )),
                new Train("Argo Bromo Anggrek", "Jakarta", "Surabaya", LocalDateTime.now(), Arrays.asList(
                        new Railcar("Ekonomi Premium 1", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        )),
                        new Railcar("Bisnis 1", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        ))
                )),
                new Train("Gajayana", "Jakarta", "Malang", LocalDateTime.now(), Arrays.asList(
                        new Railcar("Eksekutif 1", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        )),
                        new Railcar("Eksekutif 2", Arrays.asList(
                                new SeatRow("A", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, true); put(9, true); put(10, false);
                                }}),
                                new SeatRow("B", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, true); put(3, false); put(4, false); put(5, false); put(6, false); put(7, false); put(8, false); put(9, false); put(10, false);
                                }}),
                                new SeatRow("C", new HashMap<Integer, Boolean>() {{
                                    put(1, false); put(2, false); put(3, true); put(4, false); put(5, false); put(6, true); put(7, false); put(8, false); put(9, false); put(10, true);
                                }}),
                                new SeatRow("D", new HashMap<Integer, Boolean>() {{
                                    put(1, true); put(2, false); put(3, false); put(4, false); put(5, false); put(6, true); put(7, false); put(8, true); put(9, true); put(10, true);
                                }})
                        ))
                ))
        );
    }

    @Override
    public Double getDiscount(String voucher) {
        return 0.0;
    }

    @Override
    public Double calculateBill(String from, String to, String voucher) {
        return 0.0;
    }
}
