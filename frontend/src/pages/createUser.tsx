import * as React from "react";
import { DatePicker } from "@fluentui/react-datepicker-compat";
import { Field, Input, Body1, Button, Card, CardFooter, CardHeader } from "@fluentui/react-components";
import '../index.css';

const createUserPage = () => (
  <div 
    style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      height: '100vh',
      padding: '20px',
    }}
  >
    <Card style={{ width: '500px', padding: '20px' }}>
      <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '16px' }}>
        <CardHeader
          header={<Body1><b>Create User</b></Body1>}
        />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '12px', marginTop: '0px' }}>
        <Field label="E-mail"><Input className="myInput" /></Field>
        <Field label="Username"><Input className="myInput" /></Field>
        <Field label="Password"><Input className="myInput" /></Field>
        
        <div style={{ display: 'flex', gap: '12px' }}>
          <Field label="First Name" style={{ flex: 1 }}>
            <Input className="myInput" />
          </Field>
          <Field label="Last Name" style={{ flex: 1 }}>
            <Input className="myInput" />
          </Field>
        </div>
        
        <Field label="Phone Number"><Input className="myInput" /></Field>
        <Field><DatePicker placeholder="Select a date..." /></Field>
      </div>
      <CardFooter style={{ display: 'flex', justifyContent: 'center', marginTop: '16px' }}>
        <Button>Register</Button>
      </CardFooter>
    </Card>
  </div>
);

export default createUserPage;
