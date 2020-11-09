package ui.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

//                          <!--
//                         src="http://a1.phobos.apple.com/us/r1000/000/Features/atv/AutumnResources/videos/b1-1.mov"
//                         -->


@ViewScoped
@Named("testController")
public class TestController implements Serializable {
    private static final long serialVersionUID = 1L;

    private String events = "";

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public void onPlay() {
        events = "play" + "\n" + events;
    }
    public void onPause() {
        events = "pause" + "\n" + events;
    }

    public void onSeeking() {
        events = "seeking" + "\n" + events;
    }

    public void onCanplaythrough() {
        events = "can play through" + "\n" + events;
    }

    public void onLoadeddata() {
        events = "loaded data" + "\n" + events;
    }
}
