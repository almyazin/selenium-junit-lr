package com.lohika.example.pages;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MyPageFactory {

  public static <T extends Page> T getPage(WebDriver driver, Class<T> pageClass) {
    T page = instantiatePage(driver, pageClass);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), page);
    page.driver = driver;
    if (! page.isOnThisPage()) {
      page.tryToOpen();
      if (! page.isOnThisPage()) {
        throw new Error("Can't open page " + pageClass);
      }
    }
    return page;
  }

  private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
    try {
      try {
        Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
        return constructor.newInstance(driver);
      } catch (NoSuchMethodException e) {
        return pageClassToProxy.newInstance();
      }
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
