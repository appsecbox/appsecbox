import Header from "./menu/Header";
import Routing from "./router/Routing";
import {Container} from "@material-ui/core";


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
