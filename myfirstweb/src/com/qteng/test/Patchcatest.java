package com.qteng.test;

import org.junit.Test;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by xieyue on 2016/6/13.
 */
public class Patchcatest {

    @Test
    public void test() throws Exception {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        RandomWordFactory factory = new RandomWordFactory();
        factory.setMinLength(6);
        factory.setMaxLength(8);
        cs.setWordFactory(factory);
        FileOutputStream fos = new FileOutputStream("E:/as.png");
        EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
        fos.flush();
        fos.close();
    }

}
