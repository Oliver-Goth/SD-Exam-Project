import React from 'react';
import { Text, Input, Field, List, ListItem, Body1, Button, Card, CardFooter, CardHeader } from "@fluentui/react-components";
import '../index.css';



const Payment = () => {
  return (
    <div className="paymentWrapper">
      {/* Payment Card */}
      <Card className="paymentCard">
        <div className="paymentCardHeader">
          <CardHeader header={<Body1><b>Payment</b></Body1>} />
        </div>
        <Field label="Name on Card">
          <Input className="myInput" />
        </Field>
        <Field label="Card Number">
          <Input className="myInput" />
        </Field>
        <div style={{ display: 'flex', gap: '12px' }}>
          <Field label="Expiry date" className="expiryField">
            <Input className="myInput" />
          </Field>
          <Field label="CVV" className="cvvField">
            <Input className="myInput" />
          </Field>
        </div>
        <CardFooter className="paymentCardFooter">
          <Button className="fui-Button">Pay</Button>
        </CardFooter>
      </Card>

      {/* Subscription Card */}
      <Card className="paymentCard">
        <div className="paymentCardHeader">
          <CardHeader header={<Body1><b>Title $50/mo</b></Body1>} />
        </div>
        <div className="paymentList">
          <List>
            <ListItem><Text>Asia</Text></ListItem>
            <ListItem><Text>Europe</Text></ListItem>
            <ListItem><Text>Americas</Text></ListItem>
            <ListItem><Text>Africa</Text></ListItem>
            <ListItem><Text>Oceania</Text></ListItem>
          </List>
        </div>
        <CardFooter className="paymentCardFooter">
          <Button className="fui-Button">Button</Button>
        </CardFooter>
      </Card>
    </div>
  );
}

export default Payment;
