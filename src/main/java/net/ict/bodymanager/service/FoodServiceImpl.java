package net.ict.bodymanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.FoodDTO;
import net.ict.bodymanager.entity.Food;
import net.ict.bodymanager.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FoodServiceImpl implements FoodService {
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;



}
