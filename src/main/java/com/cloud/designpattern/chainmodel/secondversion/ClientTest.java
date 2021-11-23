package com.cloud.designpattern.chainmodel.secondversion;

import com.cloud.designpattern.chainmodel.firstversion.PreparationList;
import com.cloud.designpattern.chainmodel.firstversion.Study;
import org.junit.jupiter.api.Test;

/**
 * @version v1.0
 * @ClassName ClientTest
 * @Author rayss
 * @Datetime 2021/7/21 9:52 上午
 */

public class ClientTest {

    @Test
    public void testResponsibilityAdvance() {
        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(true);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(true);

        Study study = new Study();

        StudyPrepareFilter haveBreakfastFilter =
                new HaveBreakfastFilter();
        StudyPrepareFilter washHairFilter = new WashHairFilter();
        StudyPrepareFilter washFaceFilter = new WashFaceFilter();

        FilterChain filterChain = new FilterChain(study);
        filterChain.addFilter(washFaceFilter);
        filterChain.addFilter(washHairFilter);
        filterChain.addFilter(haveBreakfastFilter);

        filterChain.doFilter(preparationList, filterChain);
    }
}
