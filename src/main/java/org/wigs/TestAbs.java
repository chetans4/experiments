package org.wigs;

abstract class TestAbs {

}

abstract class TestAbsIn {
    protected class OneCls{

    }

}

final class OneCls{

    private OneCls() {
    }

    protected static OneCls createOneCls() {
        return new OneCls();
    }
}
