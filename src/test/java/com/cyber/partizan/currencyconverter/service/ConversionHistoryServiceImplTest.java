package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import com.cyber.partizan.currencyconverter.repository.ConversionHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConversionHistoryServiceImplTest {

    @Mock
    ConversionHistoryRepository conversionHistoryRepository;

    @InjectMocks
    ConversionHistoryServiceImpl conversionHistoryService;

    @Test
    void save() {
        ConversionHistory conversionHistory = new ConversionHistory();
        conversionHistoryService.save(conversionHistory);
        verify(conversionHistoryRepository).save(any());
    }

    @Test
    void findHistory() {
        String c1 = "R01011";
        String c2 = "R01010";
        LocalDate now = LocalDate.now();
        conversionHistoryService.findHistory(now, now.plusDays(1), c1, c2);
        verify(conversionHistoryRepository).findHistory(now, now.plusDays(1), c1, c2);
    }
}