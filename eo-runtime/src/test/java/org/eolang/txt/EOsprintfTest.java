/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2021 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.txt;

import org.eolang.EOint;
import org.eolang.EOstring;
import org.eolang.phi.Data;
import org.eolang.phi.PhEta;
import org.eolang.phi.PhWith;
import org.eolang.phi.Phi;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link EOsprintf}.
 *
 * @since 0.1
 */
public final class EOsprintfTest {

    @Test
    public void printsString() {
        final Phi format = new PhWith(
            new EOstring(),
            "data",
            new Data.Value<>("Hello, %d!")
        );
        final Phi num = new EOint();
        num.attr("data").put(new Data.Value<>(1L));
        final Phi phi = new PhWith(
            new PhWith(
                new EOsprintf(new PhEta()),
                "format",
                format
            ),
            "args",
            num
        );
        MatcherAssert.assertThat(
            new Data.Take(phi).take(String.class),
            Matchers.equalTo("Hello, 1!")
        );
    }

    @Test
    public void printsStringWithVarargs() {
        final Phi format = new PhWith(
            new EOstring(),
            "data",
            new Data.Value<>("Hello, %s %s!")
        );
        final Phi num = new PhWith(
            new EOint(), "data", new Data.Value<>(5L)
        );
        Phi phi = new EOsprintf(new PhEta());
        phi = phi.copy();
        phi = new PhWith(phi, 0, format);
        phi = new PhWith(phi, 1, num);
        phi = new PhWith(phi, 2, num);
        MatcherAssert.assertThat(
            new Data.Take(phi).take(String.class),
            Matchers.equalTo("Hello, 5 5!")
        );
    }

}
