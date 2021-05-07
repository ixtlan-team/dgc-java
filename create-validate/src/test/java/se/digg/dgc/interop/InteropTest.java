/*
 * MIT License
 *
 * Copyright 2021 Myndigheten för digital förvaltning (DIGG)
 */
package se.digg.dgc.interop;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Executes test cases collected from <a href=
 * "https://github.com/eu-digital-green-certificates/dgc-testdata">https://github.com/eu-digital-green-certificates/dgc-testdata</a>.
 * 
 * @author Martin Lindström (martin@idsec.se)
 * @author Henrik Bengtsson (extern.henrik.bengtsson@digg.se)
 * @author Henric Norlander (extern.henric.norlander@digg.se)
 */
@RunWith(Parameterized.class)
public class InteropTest {

  /** Directory where we have the test files. */
  private static final String BASEDIR = "src/test/resources/interop/eu";

  /** Name of test. */
  private final String testName;

  /** Path to test file. */
  private final String testFile;

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        // Doesn't work. They pass in the entire CWT instead of just the DGC payload of the CWT
        { "BG-1", "BG/1.json" },
        { "RO-1", "RO/1.json" },
        { "RO-2", "RO/2.json" },
        { "SE-1", "SE/1.json" }
    });
  }

  /**
   * Constructor.
   * 
   * @param testName
   *          name of test
   * @param testFile
   *          test file
   */
  public InteropTest(final String testName, final String testFile) {
    this.testName = testName;
    this.testFile = BASEDIR + "/" + testFile;
  }

  /**
   * Executes the test.
   * 
   * @throws Exception
   *           for errors
   */
  @Test
  public void validate() throws Exception {
    final TestStatement testStatement = DGCTestDataVerifier.getTestStatement(this.testFile);
    DGCTestDataVerifier.validate(this.testName, testStatement);
  }

}
