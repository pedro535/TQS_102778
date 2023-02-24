package geocoding;

import java.util.Objects;

public class Address {

    private String road;
    private String cirty;
    private String state;
    private String zio;
    private String houseNumber;

    public Address(String road, String cirty, String state, String zio, String houseNumber) {
        this.road = road;
        this.cirty = cirty;
        this.state = state;
        this.zio = zio;
        this.houseNumber = houseNumber;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCirty() {
        return cirty;
    }

    public void setCirty(String cirty) {
        this.cirty = cirty;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZio() {
        return zio;
    }

    public void setZio(String zio) {
        this.zio = zio;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "road=" + road + ", cirty=" + cirty + ", state=" + state + ", zio=" + zio + ", houseNumber=" + houseNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.road);
        hash = 97 * hash + Objects.hashCode(this.cirty);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + Objects.hashCode(this.zio);
        hash = 97 * hash + Objects.hashCode(this.houseNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.road, other.road)) {
            return false;
        }
        if (!Objects.equals(this.cirty, other.cirty)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zio, other.zio)) {
            return false;
        }
        if (!Objects.equals(this.houseNumber, other.houseNumber)) {
            return false;
        }
        return true;
    }


}
