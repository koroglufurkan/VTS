import React from "react";
//No extension if js file
import ProfileCard from "./ProfileCard";
//Import only if images are local
import AlexaImage from "./images/alexa.png"
import SiriImage from "./images/siri.png"
import CortanaImage from "./images/cortana.png"
import "bulma/css/bulma.css"
function App() {
    return (
        <div>
            <h1>Vessel Traffic Service</h1>

            <section className="hero is-primary">
                <div className="hero-body">
                    <p className="title">
                        <div>Digital Assistants</div>
                    </p>
                </div>
            </section>

            <section className="is-center">
                <input type={"number"} maxLength={4}
                       autoFocus placeholder={"hi there"}/>
            </section>

            <section>
                <div className="container is-left">
                    <div className="columns">
                        <div className="columns">
                            <div className="column is-4">
                                <ProfileCard title = "Alexa" handle = "@alexa99"
                                     img = {AlexaImage} alt = "It is Alexa Image"
                                             description = "You know alexa, she is very popular"
                                />
                            </div>
                            <div className="column is-4">
                                <ProfileCard title = "Cortana" handle = "@cortana32"
                                     img = {CortanaImage} alt = "It is Cortana Image"
                                             description = "You know Cortana, she is very popular"
                                />
                            </div>
                            <div className="column is-4">
                                <ProfileCard title = "Siri" handle = "@siri01"
                                     img = {SiriImage} alt={"It is Siri image"}
                                             description = "You know Siri, she is very popular"
                                />

                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}
export default App;