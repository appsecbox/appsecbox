import {Route, Switch} from "react-router-dom";
import Dashboard from "../dashboard/Dashboard";
import ItemsList from "../ui/ItemsList";
import Api from "../api/Api";
import {Button} from "@material-ui/core";
import ApplicationForm from "../application/ApplicationForm";

const Routing = () => (
    <Switch>
        <Route exact path={"/application"} render={(props) => (
            <span>
                    <ApplicationForm></ApplicationForm>
                <br />
                <ItemsList getDataHandle={Api.getApplications()}/>
                </span>
        )}/>
        <Route exect path={"/"} component={Dashboard}/>
    </Switch>
)

export default Routing