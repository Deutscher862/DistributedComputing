//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.7
//
// <auto-generated>
//
// Generated from file `devices.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SmartHome;

public class NightMode implements java.lang.Cloneable,
                                  java.io.Serializable
{
    public boolean nightModeEnabled;

    public NightMode()
    {
    }

    public NightMode(boolean nightModeEnabled)
    {
        this.nightModeEnabled = nightModeEnabled;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        NightMode r = null;
        if(rhs instanceof NightMode)
        {
            r = (NightMode)rhs;
        }

        if(r != null)
        {
            if(this.nightModeEnabled != r.nightModeEnabled)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::SmartHome::NightMode");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, nightModeEnabled);
        return h_;
    }

    public NightMode clone()
    {
        NightMode c = null;
        try
        {
            c = (NightMode)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeBool(this.nightModeEnabled);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.nightModeEnabled = istr.readBool();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, NightMode v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public NightMode ice_read(com.zeroc.Ice.InputStream istr)
    {
        NightMode v = new NightMode();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<NightMode> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, NightMode v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(1);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<NightMode> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(NightMode.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final NightMode _nullMarshalValue = new NightMode();

    /** @hidden */
    public static final long serialVersionUID = 10176095L;
}
