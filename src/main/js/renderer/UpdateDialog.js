const React = require('react');
const ReactDOM = require('react-dom');

export default class UpdateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const updatedEmployee = {};
        this.props.attributes.forEach(attribute => {
            updatedEmployee[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onUpdate(this.props.employee, updatedEmployee);
        window.location = "#";
    }

    render() {
        const employee = this.props.employee
        const { entity } = employee
        const inputs = this.props.attributes.map(attribute =>
            <p key={employee.entity[attribute]}>
                <input type="text" placeholder={attribute}
                       defaultValue={entity[attribute]}
                       ref={attribute} className="field"/>
            </p>
        );

        const dialogId = "updateEmployee-" + entity._links.self.href;

        const isManagerCorrect = entity.manager && entity.manager.name == this.props.loggedInManager;

        if (isManagerCorrect) {
            return (
                <div>
                    <a href={"#" + dialogId}>Update</a>

                    <div id={dialogId} className="modalDialog">
                        <div>
                            <a href="#" title="Close" className="close">X</a>

                            <h2>Update an employee</h2>

                            <form>
                                {inputs}
                                <button onClick={this.handleSubmit}>Update</button>
                            </form>
                        </div>
                    </div>
                </div>
            )
        } else {
            return (
                <div>
                    <a>Not Your Employee</a>
                </div>
            )
        }
    }

}