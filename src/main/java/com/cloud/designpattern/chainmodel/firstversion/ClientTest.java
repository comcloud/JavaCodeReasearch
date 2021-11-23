package com.cloud.designpattern.chainmodel.firstversion;

import com.cloud.designpattern.chainmodel.firstversion.*;
import com.cloud.designpattern.chainmodel.secondversion.FilterChain;
import com.cloud.designpattern.chainmodel.secondversion.StudyPrepareFilter;
import org.junit.jupiter.api.Test;


/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/7/21 9:19 上午
 */
public class ClientTest {
    @Test
    public void testResponsibility() {
        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(true);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(true);

        Study study = new Study();

        AbstractPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter(null);
        AbstractPrepareFilter washFaceFilter = new WashFaceFilter(haveBreakfastFilter);
        AbstractPrepareFilter washHairFilter = new WashHairFilter(washFaceFilter);

        washHairFilter.doFilter(preparationList, study);
    }


}
