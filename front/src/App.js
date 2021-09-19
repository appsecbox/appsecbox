import Header from "./menu/Header";
import {Container} from "@material-ui/core";
import Routing from "./router/Routing";


function App() {
    return (
        <div>
            <Header></Header>
            <Container>
                <br/>
                <Routing></Routing>
            </Container>
        </div>
    );
}

export default App;
