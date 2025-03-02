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
package org.eolang.phi;

import org.eolang.EOint;
import org.eolang.txt.EOsprintf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link PhDefault}.
 *
 * @since 0.1
 */
public final class PhDefaultTest {

    @Test
    public void makesCopy() {
        final Phi num = new PhWith(new EOint(), "data", new Data.Value<>(42L));
        final Phi parent = new EOsprintf(new PhEta());
        final Phi phi = new PhDefaultTest.Foo(parent);
        phi.attr(0).put(num);
        final Phi copy = phi.copy();
        MatcherAssert.assertThat(
            new Data.Take(copy).take(String.class),
            Matchers.equalTo("Hello, world!")
        );
        Assertions.assertThrows(
            Attr.Exception.class,
            () -> new Data.Take(new PhMethod(copy, "x")).take(Long.class)
        );
        MatcherAssert.assertThat(
            phi.attr("x").get().attr("data"),
            Matchers.notNullValue()
        );
        Assertions.assertThrows(
            Attr.Exception.class,
            () -> copy.attr(0).get().attr("data")
        );
    }

    public static class Foo extends PhDefault {
         public Foo(final Phi parent) {
             super(parent);
             this.add("x", new AtFree());
             this.add("_origin", new AtBound(new AtLambda(self -> new PhWith(
                 new org.eolang.EOstring(),
                 "data",
                 new Data.Value<>("Hello, world!")
             ))));
        }
    }

}
