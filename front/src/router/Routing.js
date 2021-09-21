import {Route, Switch} from "react-router-dom";
import Dashboard from "../dashboard/Dashboard";
import ItemsList from "../ui/ItemsList";
import Api from "../api/Api";
import ApplicationForm from "../application/ApplicationForm";
import ApplicationPage from "../application/ApplicationPage";

const Routing = () => (
    <Switch>
        <Route exact path={"/application/:id"} component={ApplicationPage}/>

        <Route exact path={"/application"} render={(props) => (
            <span>
                <ApplicationForm></ApplicationForm>
                <br/>
                <ItemsList type={"application"} getDataPromise={Api.getApplications()}/>
            </span>
        )}/>

        <Route exect path={"/"} component={Dashboard}/>
    </Switch>
)

export default Routing