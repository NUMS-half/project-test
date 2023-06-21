package com.sisp.service;

import com.sisp.dao.entity.OptionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OptionServiceTest {

    @Autowired
    private OptionService optionService;

    @Test
    public void addOption() {
    }

    @Test
    public void insertBatch() {
        List<OptionEntity> list = new ArrayList<>();
        OptionEntity option = new OptionEntity();
        option.setQuestionId("315bebff724c490190f3a59b935bf7e2");
        option.setChooseTerm("123");
        option.setFraction("1");

        OptionEntity option2 = new OptionEntity();
        option2.setQuestionId("0");
        option2.setChooseTerm("1234");
        option2.setFraction("2");

        OptionEntity option3 = new OptionEntity();
        option3.setQuestionId("315bebff724c490190f3a59b935bf7e2");
        option3.setChooseTerm("12345");
        option3.setFraction("3");

        list.add(option);
        list.add(option2);
        list.add(option3);

        optionService.insertBatch(list);
    }
}